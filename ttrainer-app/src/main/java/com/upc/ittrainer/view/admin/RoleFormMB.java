/*
 * RoleFormMB.java
 */
package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.service.admin.CapabilityService;
import com.upc.ittrainer.service.admin.RoleService;
import com.upc.ittrainer.view.FormMB;
import lombok.Getter;
import org.omnifaces.util.Faces;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author guille.fernandezp26@gmail.com
 */
@Named
@ViewScoped
public class RoleFormMB extends FormMB<Role, String> {

    @Autowired
    private CapabilityService capabilityService;

    @Getter
    private List<Capability> capabilities;

    @Inject
    public RoleFormMB(RoleService service) {
        super(service);
    }

    @Override
    public void init() {
        super.init();
        capabilities = capabilityService.findAll();
    }

    @Override
    public String listView() {
        return "admin/roles/roles.xhtml";
    }

}