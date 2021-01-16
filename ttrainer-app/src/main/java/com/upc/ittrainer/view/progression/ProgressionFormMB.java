package com.upc.ittrainer.view.progression;

import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.model.progression.Progression;
import com.upc.ittrainer.model.progression.ProgressionValue;
import com.upc.ittrainer.model.progression.Scope;
import com.upc.ittrainer.service.client.ClientService;
import com.upc.ittrainer.service.progression.ProgressionService;
import com.upc.ittrainer.view.FormMB;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.adminfaces.template.util.Assert.has;

/**
 * @author guille.fernandezp26@gmail.com
 */
@Named
@ViewScoped
public class ProgressionFormMB extends FormMB<Progression, Integer> {

    private final ClientService clientService;
    @Getter
    private List<Client> clients;

    @Getter
    @Setter
    private Double weight = 0d;
    @Getter
    @Setter
    private Double abdominalFat = 0d;
    @Getter
    @Setter
    private Double basalMetabolism = 0d;
    @Getter
    @Setter
    private Double fatPercentage = 0d;
    @Getter
    @Setter
    private Double metabolicAge = 0d;
    @Getter
    @Setter
    private Double muscleWeight = 0d;

    @Inject
    public ProgressionFormMB(ProgressionService service,
                             ClientService clientService) {
        super(service);
        this.clientService = clientService;

        clients = clientService.findAllByUser();
    }

    @Override
    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }

        super.init();

        if (has(getId())) {

            weight = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("WEIGHT"))
                    .findFirst().orElse(new ProgressionValue()).getValue();

            abdominalFat = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("ABDOMINAL_FAT"))
                    .findFirst().orElse(new ProgressionValue()).getValue();

            basalMetabolism = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("BASAL_METABOLISM"))
                    .findFirst().orElse(new ProgressionValue()).getValue();

            fatPercentage = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("FAT_PERCENTAGE"))
                    .findFirst().orElse(new ProgressionValue()).getValue();

            metabolicAge = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("METABOLIC_AGE"))
                    .findFirst().orElse(new ProgressionValue()).getValue();

            muscleWeight = getEntity().getProgressionValues()
                    .stream().filter(pv -> pv.getScope().getName().equals("MUSCLE_WEIGHT"))
                    .findFirst().orElse(new ProgressionValue()).getValue();
        }
    }

    @Override
    public void save() {
        Set<ProgressionValue> progressionValues = new HashSet<>();

        ProgressionValue weigthValue = new ProgressionValue(
                getEntity(),
                new Scope("WEIGHT"),
                weight);
        progressionValues.add(weigthValue);

        ProgressionValue abdominalFatValue = new ProgressionValue(
                getEntity(),
                new Scope("ABDOMINAL_FAT"),
                weight);
        progressionValues.add(abdominalFatValue);

        ProgressionValue basalValue = new ProgressionValue(
                getEntity(),
                new Scope("BASAL_METABOLISM"),
                weight);
        progressionValues.add(basalValue);

        ProgressionValue fatValue = new ProgressionValue(
                getEntity(),
                new Scope("FAT_PERCENTAGE"),
                weight);
        progressionValues.add(fatValue);

        ProgressionValue metabolicValue = new ProgressionValue(
                getEntity(),
                new Scope("METABOLIC_AGE"),
                weight);
        progressionValues.add(metabolicValue);

        ProgressionValue muscleWeightValue = new ProgressionValue(
                getEntity(),
                new Scope("MUSCLE_WEIGHT"),
                weight);
        progressionValues.add(muscleWeightValue);

        getEntity().setProgressionValues(progressionValues);

        super.save();
    }

    @Override
    public String listView() {
        return "progression/progressions.xhtml";
    }

}
