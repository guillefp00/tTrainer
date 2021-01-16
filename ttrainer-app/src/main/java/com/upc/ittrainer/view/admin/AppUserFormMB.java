package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.view.FormMB;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.service.admin.AppUserService;
import com.upc.ittrainer.service.admin.RoleService;
import lombok.Getter;
import org.omnifaces.util.Faces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author guille.fernandezp26@gmail.com
 */
@Named
@ViewScoped
public class AppUserFormMB extends FormMB<AppUser, String> {

    @Getter
    private List<Role> roles;
    private final RoleService roleService;

    @Inject
    public AppUserFormMB(AppUserService service, final RoleService roleService) {
        super(service);
        this.roleService = roleService;
    }

    @Override
    public void init() {
        super.init();
        roles = roleService.findAll();
    }

    @Override
    public String listView() {
        return "admin/users/users.xhtml";
    }

}
