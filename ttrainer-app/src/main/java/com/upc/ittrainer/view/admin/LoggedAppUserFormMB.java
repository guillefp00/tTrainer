package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.infra.security.LogonService;
import com.upc.ittrainer.service.admin.AppUserService;
import com.upc.ittrainer.service.admin.RoleService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author guille.fernandezp26@gmail.com
 */
@Named
@ViewScoped
public class LoggedAppUserFormMB extends AppUserFormMB {

    private LogonService logonService;

    @Inject
    public LoggedAppUserFormMB(AppUserService service, LogonService logonService, final RoleService roleService) {
        super(service, roleService);
        this.logonService = logonService;

    }

    @PostConstruct
    public void postConstruct() {
        setId(logonService.getLoggedUser().getID());
    }

}
