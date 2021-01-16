package com.upc.ittrainer.view.client;


import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.service.client.ClientService;
import com.upc.ittrainer.view.EntitiesMB;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ClientsMB extends EntitiesMB<Client, Integer> {

    @Inject
    public ClientsMB(ClientService service) {
        super(service);
    }

    @Override
    public String formView() {
        return "client-form";
    }
}