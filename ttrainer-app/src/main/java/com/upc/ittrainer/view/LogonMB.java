package com.upc.ittrainer.view;

import com.github.adminfaces.template.exception.BusinessException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.upc.ittrainer.infra.security.LogonService;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.util.MessagesBean;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Named
@SessionScoped
public class LogonMB implements Serializable {

    @Getter
    private AppUser loggedUser;

    @Autowired
    private LogonService service;

    private final LoadingCache<String, Boolean> writeCapabilitiesCache = CacheBuilder.newBuilder()
            .maximumSize(500)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, Boolean>() {
                        public Boolean load(String key) {
                            return loggedUser.getRoles()
                                    .stream().map(Role::getWriteCapabilities)
                                    .flatMap(Set::stream)
                                    .map(Capability::getName)
                                    .anyMatch(c -> c.equals(key));
                        }
                    });


    @PostConstruct
    public void init() {
        try {
            loggedUser = service.getLoggedUser();
        } catch (BusinessException ex) {
            MessagesBean.addDetailMessage(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public boolean hasWriteCapability(final String capability) {
        try {
            return writeCapabilitiesCache.get(capability);
        } catch (ExecutionException e) {
            throw new BusinessException(e);
        }
    }

}
