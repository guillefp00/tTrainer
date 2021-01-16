package com.upc.ittrainer.view.admin;

import com.upc.ittrainer.view.FormMB;
import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.service.admin.CapabilityService;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CapabilityFormMB extends FormMB<Capability, String> {

    @Inject
    public CapabilityFormMB(CapabilityService service) {
        super(service);
    }

    @Override
    public String listView() {
        return "admin/capabilities/capabilities.xhtml";
    }
}
