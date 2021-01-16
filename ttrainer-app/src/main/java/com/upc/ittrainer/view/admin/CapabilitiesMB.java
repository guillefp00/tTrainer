package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.view.EntitiesMB;
import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.service.admin.CapabilityService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CapabilitiesMB extends EntitiesMB<Capability, String> {

    @Inject
    public CapabilitiesMB(CapabilityService service) {
        super(service);
    }

    @Override
    public String formView() {
        return "capability-form";
    }
}
