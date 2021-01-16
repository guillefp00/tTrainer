package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.service.admin.AppUserService;
import com.upc.ittrainer.service.admin.RoleService;
import com.upc.ittrainer.view.EntitiesMB;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

@Named
@ViewScoped
public class AppUsersMB extends EntitiesMB<AppUser, String> {

    @Getter
    private String[] roles;
    private final RoleService roleService;

    @Inject
    public AppUsersMB(AppUserService service, final RoleService roleService) {
        super(service);
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        roles = roleService.findAll().stream().map(ble -> ble.getID()).toArray(String[]::new);
    }

    @Override
    public String formView() {
        return "appuser-form";
    }

    public Role[] rolesValues() {
        return getEntities().stream().map(AppUser::getRoles).flatMap(Collection::stream).distinct().toArray(Role[]::new);
    }
}