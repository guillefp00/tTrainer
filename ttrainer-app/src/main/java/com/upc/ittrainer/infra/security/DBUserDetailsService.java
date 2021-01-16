package com.upc.ittrainer.infra.security;

import com.google.common.base.Strings;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.service.admin.AppUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DBUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private AppUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (Strings.isNullOrEmpty(username)) {
            throw new UsernameNotFoundException("Invalid username: Null or empty.");
        }

        final AppUser user = userService.findById(username.toLowerCase());
        LOGGER.info("Login user {} from db", username.toLowerCase());

        return new AppUserDetails(user);
    }

}
