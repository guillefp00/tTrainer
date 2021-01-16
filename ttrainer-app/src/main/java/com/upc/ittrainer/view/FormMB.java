/*
 * FormMB.java
 */
package com.upc.ittrainer.view;

import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.service.Service;
import com.upc.ittrainer.util.MessagesBean;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;

import java.io.IOException;
import java.io.Serializable;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author guille.fernandezp26@gmail.com
 */
public abstract class FormMB<T extends ModelEntity<ID>, ID extends Comparable> implements Serializable {

    public static final String MODEL_ENTITY = "ModelEntity ";
    @Getter
    @Setter
    private ID id;

    @Getter
    @Setter
    private T entity;

    @Getter
    private Service<T, ID> service;

    public FormMB(Service<T, ID> service) {
        this.service = service;
    }

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (has(id)) {
            entity = service.findById(id);
        } else {
            entity = service.newEntityInstance();
        }
    }

    public void remove() throws IOException {
        if (has(entity) && has(entity.getID())) {
            service.remove(entity);
            MessagesBean.addDetailMessage(MODEL_ENTITY + entity.getID()
                    + " " + generateRemoveMessageDetails() + " successfully");
            Faces.getFlash().setKeepMessages(true);
            Faces.redirect(listView());
        }
    }

    private String generateRemoveMessageDetails() {
        return "deleted";
    }

    public abstract String listView();

    public void save() {
        String msg;
        if (isNew()) {
            entity = service.insert(entity);
            msg = MODEL_ENTITY + entity.getID() + " created successfully";
            id = entity.getID();
        } else {
            entity = service.update(entity);
            msg = MODEL_ENTITY + entity.getID() + " updated successfully";
        }
        MessagesBean.addDetailMessage(msg);
    }

    public boolean isNew() {
        return entity == null || entity.isNew();
    }

    public void clear() {
        entity = service.newEntityInstance();
        id = null;
    }

}