package com.upc.ittrainer.view.schedule;

import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.model.schedule.Event;
import com.upc.ittrainer.service.client.ClientService;
import com.upc.ittrainer.service.schedule.EventService;
import com.upc.ittrainer.view.EntitiesMB;
import lombok.Getter;
import org.omnifaces.util.Faces;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Named
@ViewScoped
public class ScheduleMB extends EntitiesMB<Event, Integer> {

    private ScheduleModel eventModel;

    private ScheduleEvent<Client> event = new DefaultScheduleEvent();

    @Getter
    private List<Client> clients;

    private final ClientService clientService;

    @Inject
    public ScheduleMB(EventService service, ClientService clientService) {
        super(service);
        this.clientService = clientService;
    }

    @PostConstruct
    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        clients = clientService.findAllByUser();
        load();
        loadSchedule();
    }

    private void loadSchedule() {
        eventModel = new DefaultScheduleModel();
        for (Event event : getEntities()) {
            eventModel.addEvent(parseEvent(event));
        }
    }

    private DefaultScheduleEvent<Client> parseEvent(Event event) {
        return DefaultScheduleEvent.<Client>builder()
                .title(event.getTitle())
                .startDate(event.getInit())
                .endDate(event.getEnd())
                .data(event.getClient())
                .description(event.getID().toString())
                .build();
    }

    private Event parseEvent(ScheduleEvent<Client> schuleEvent) {
        final Event event = new Event();
        event.setClient((Client) schuleEvent.getData());
        event.setTitle(schuleEvent.getTitle());
        event.setInit(schuleEvent.getStartDate());
        event.setEnd(schuleEvent.getEndDate());
        if (schuleEvent.getDescription() != null) {
            event.setId(new Integer(schuleEvent.getDescription()));
        }
        return event;
    }

    @Override
    public String formView() {
        return "";
    }

    public LocalDateTime getRandomDate(LocalDateTime base) {
        long minDay = base.toLocalDate().toEpochDay();
        long maxDay = LocalDate.of(2030, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDateTime.of(LocalDate.ofEpochDay(randomDay), LocalTime.MAX);
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
            getService().insert(parseEvent(event));
        } else {
            updateEvent();
        }

        event = new DefaultScheduleEvent();
    }

    private void updateEvent() {
        eventModel.updateEvent(event);
        getService().update(parseEvent(event));
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (LocalDateTime) selectEvent.getObject(), ((LocalDateTime) selectEvent.getObject()).plusHours(1));
    }

    public void onEventMove(ScheduleEntryMoveEvent moveEvent) {
        event = (ScheduleEvent) moveEvent.getScheduleEvent();
        updateEvent();
    }

    public void onEventResize(ScheduleEntryResizeEvent resizeEvent) {
        event = (ScheduleEvent) resizeEvent.getScheduleEvent();
        updateEvent();
    }

}
