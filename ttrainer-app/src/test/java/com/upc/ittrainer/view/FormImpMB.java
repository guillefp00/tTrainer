package com.upc.ittrainer.view;

import com.upc.ittrainer.model.ModelEntityImp;
import com.upc.ittrainer.service.ServiceImp;

public class FormImpMB extends FormMB<ModelEntityImp, Integer> {

    public static String LIST_VIEW = "list";

    public FormImpMB(ServiceImp service) {
        super(service);
    }

    @Override
    public String listView() {
        return LIST_VIEW;
    }
}
