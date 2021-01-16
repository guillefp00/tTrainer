package com.upc.ittrainer.view;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.service.Service;
import com.upc.ittrainer.util.MessagesBean;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.ToggleSelectEvent;

import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class EntitiesMB<T extends ModelEntity<ID>, ID extends Comparable>
        implements Serializable {

    @Getter
    private final Service<T, ID> service;
    @Getter
    @Setter
    private List<T> entities = new ArrayList<>();
    @Getter
    @Setter
    private List<T> selectedEntities = new ArrayList<>(); // entities selected in checkbox column

    private boolean toggleSelected = false;

    public EntitiesMB(Service<T, ID> service) {
        this.service = service;
    }

    public void delete() {
        int numEntities = 0;
        Iterator<T> selectedEntityIterator = selectedEntities.iterator();
        while (selectedEntityIterator.hasNext()) {
            T selectedEntity = selectedEntityIterator.next();
            numEntities++;
            service.remove(selectedEntity);
            selectedEntityIterator.remove();
        }
        entities.clear();
        findAll();
        MessagesBean.addDetailMessage(numEntities + " entities " + generateRemoveMessageDetails() + " successfully!");
    }

    public void load() {
        findAll();
    }


    protected String generateRemoveMessageDetails() {
        return "deleted";
    }

    public List<T> findAll() {
        try {
            entities = service.findAll();
        } catch (BusinessException e) {
            MessagesBean.addDetailMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
            entities = new ArrayList<>();
        }
        return entities;
    }

    public boolean disableDelete() {
        return selectedEntities.isEmpty() || toggleSelected;
    }

    public void toggleSelect(ToggleSelectEvent tse) {
        toggleSelected = tse.isSelected();
    }

    public abstract String formView();
}
