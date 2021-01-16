package com.upc.ittrainer.service.progression;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.infra.security.LogonService;
import com.upc.ittrainer.model.progression.Progression;
import com.upc.ittrainer.repository.progression.ProgressionJPARepository;
import com.upc.ittrainer.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@org.springframework.stereotype.Service
public class ProgressionService extends Service<Progression, Integer> {

    private final LogonService logonService;

    private static final Logger LOGGER = LogManager.getLogger();

    public ProgressionService(ProgressionJPARepository repository, LogonService logonService) {
        super(repository);

        this.logonService = logonService;
    }

    public List<Progression> findAllByUser() {
        try {
            return ((ProgressionJPARepository) repository)
                    .findByClientAppUser(logonService.getLoggedUser());
        } catch (Exception e) {
            LOGGER.error("Error in findAllByUser.", e);
            throw new BusinessException(e);
        }
    }
}