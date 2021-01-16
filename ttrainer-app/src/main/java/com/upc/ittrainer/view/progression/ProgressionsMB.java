package com.upc.ittrainer.view.progression;

import com.upc.ittrainer.model.progression.Progression;
import com.upc.ittrainer.service.progression.ProgressionService;
import com.upc.ittrainer.view.EntitiesMB;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class ProgressionsMB extends EntitiesMB<Progression, Integer> {
    @Inject
    public ProgressionsMB(ProgressionService service) {
        super(service);
    }

    @Override
    public List<Progression> findAll() {
        List<Progression> progressions = ((ProgressionService) getService()).findAllByUser();
        setEntities(progressions);
        return progressions;
    }

    @Override
    public String formView() {
        return "progression-form";
    }

}