package com.upc.ittrainer.view.client;

import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.service.client.ClientService;
import com.upc.ittrainer.view.FormMB;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author guille.fernandezp26@gmail.com
 */
@Named
@ViewScoped
public class ClientFormMB extends FormMB<Client, Integer> {

    @Inject
    public ClientFormMB(ClientService service) {
        super(service);
    }

    @Override
    public String listView() {
        return "client/clients.xhtml";
    }

}
