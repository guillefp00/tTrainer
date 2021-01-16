package com.upc.ittrainer.infra.security;

import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.model.admin.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {

    @Getter
    private AppUser user;

    public AppUserDetails(AppUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            for (Capability c : role.getCapabilities()) {
                listOfAuthorities.add(new MyGrantedAuthority(c));
            }
        }
        return listOfAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    private static class MyGrantedAuthority implements GrantedAuthority {

        final Capability capability;

        MyGrantedAuthority(final Capability capability) {
            this.capability = capability;
        }

        @Override
        public String getAuthority() {
            return capability.getName();
        }
    }

}
