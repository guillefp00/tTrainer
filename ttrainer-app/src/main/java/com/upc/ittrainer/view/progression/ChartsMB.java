package com.upc.ittrainer.view.progression;

import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.model.progression.Progression;
import com.upc.ittrainer.model.progression.ProgressionValue;
import com.upc.ittrainer.model.progression.Scope;
import com.upc.ittrainer.service.client.ClientService;
import com.upc.ittrainer.service.progression.ProgressionService;
import com.upc.ittrainer.service.progression.ScopeService;
import com.upc.ittrainer.view.EntitiesMB;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ChartsMB extends EntitiesMB<Progression, Integer> {

    private final ClientService clientService;
    private final ScopeService scopeService;
    @Getter
    private List<Client> clients;
    @Getter
    @Setter
    private Client selectedClient;
    @Getter
    private List<Scope> scopes;
    @Getter
    @Setter
    private Scope selectedScope;

    @Getter
    private LineChartModel lineModel;

    @Inject
    public ChartsMB(ProgressionService service,
                    ClientService clientService,
                    ScopeService scopeService) {
        super(service);
        this.clientService = clientService;
        this.scopeService = scopeService;

        clients = clientService.findAllByUser();
        scopes = scopeService.findAll();
    }

    @PostConstruct
    public void init() {

        selectedClient = clients.get(0);
        selectedScope = scopes.get(0);

        createLineModel();
    }

    @Override
    public String formView() {
        return "";
    }

    private void createLineModel() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();

        dataSet.setData(chartValues());
        dataSet.setFill(false);
        dataSet.setLabel(selectedScope.getName());
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);

        data.setLabels(chartDates());

        //Options
        LineChartOptions options = new LineChartOptions();

        lineModel.setOptions(options);
        lineModel.setData(data);
    }

    private List<Object> chartValues() {
        List<Object> values = new ArrayList<>();
        for (Progression p : selectedClient.getSortedProgressions()) {
            for (ProgressionValue v : p.getProgressionValues()) {
                if (selectedScope.equals(v.getScope())) {
                    values.add(v.getValue());
                }
            }
        }

        return values;
    }

    private List<String> chartDates() {
        List<String> values = new ArrayList<>();
        for (Progression p : selectedClient.getSortedProgressions()) {
            for (ProgressionValue v : p.getProgressionValues()) {
                if (selectedScope.equals(v.getScope())) {
                    values.add(p.getDate().toString());
                }
            }
        }

        return values;
    }

    public void clientChanged() {
        createLineModel();
    }

    public void scopeChanged() {
        createLineModel();
    }

}