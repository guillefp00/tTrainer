package com.upc.ittrainer.service.schedule;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.infra.security.LogonService;
import com.upc.ittrainer.model.schedule.Event;
import com.upc.ittrainer.repository.schedule.EventJPARepository;
import com.upc.ittrainer.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@org.springframework.stereotype.Service
public class EventService extends Service<Event, Integer> {

    private final LogonService logonService;

    private static final Logger LOGGER = LogManager.getLogger();

    public EventService(EventJPARepository repository, LogonService logonService) {
        super(repository);
        this.logonService = logonService;
    }

    public List<Event> findAllByUser() {
        try {
            return ((EventJPARepository) repository)
                    .findByClientAppUser(logonService.getLoggedUser());
        } catch (Exception e) {
            LOGGER.error("Error in findAllByUser.", e);
            throw new BusinessException(e);
        }
    }

}