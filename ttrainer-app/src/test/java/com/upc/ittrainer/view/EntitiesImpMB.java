package com.upc.ittrainer.view;

import com.upc.ittrainer.model.ModelEntityImp;
import com.upc.ittrainer.service.ServiceImp;

public class EntitiesImpMB extends EntitiesMB<ModelEntityImp, Integer> {

    public static String FORM = "form";

    public EntitiesImpMB(ServiceImp service) {
        super(service);
    }

    @Override
    public String formView() {
        return FORM;
    }
}
