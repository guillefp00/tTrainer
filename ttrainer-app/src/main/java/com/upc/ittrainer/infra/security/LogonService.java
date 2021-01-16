package com.upc.ittrainer.infra.security;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.model.admin.AppUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Service
@SessionScope
public class LogonService implements Serializable {

    private AppUser loggedUser = null;

    public synchronized AppUser getLoggedUser() {
        if (loggedUser == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth instanceof AnonymousAuthenticationToken) {
                throw new BusinessException("User not logged yet!");
            }

            if (auth.getPrincipal() instanceof AppUserDetails) {
                final AppUserDetails appUserDetails = (AppUserDetails) auth.getPrincipal();
                loggedUser = appUserDetails.getUser();
            } else {
                throw new BusinessException("User not logged yet!");
            }
        }
        return loggedUser;
    }


}