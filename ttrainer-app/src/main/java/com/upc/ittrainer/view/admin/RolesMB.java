package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.view.EntitiesMB;
import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.service.admin.CapabilityService;
import com.upc.ittrainer.service.admin.RoleService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class RolesMB extends EntitiesMB<Role, String> {


    @Autowired
    private CapabilityService capabilityService;

    @Getter
    private List<Capability> capabilities;

    @Inject
    public RolesMB(RoleService service) {
        super(service);
    }

    @PostConstruct
    public void init() {
        capabilities = capabilityService.findAll();
    }

    @Override
    public String formView() {
        return "role-form";
    }

}