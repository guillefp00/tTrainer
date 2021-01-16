package com.upc.ittrainer.service.client;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.infra.security.LogonService;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.model.client.Client;
import com.upc.ittrainer.repository.client.ClientJPARepository;
import com.upc.ittrainer.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@org.springframework.stereotype.Service
public class ClientService extends Service<Client, Integer> {

    private final LogonService logonService;

    private static final Logger LOGGER = LogManager.getLogger();

    public ClientService(ClientJPARepository repository, LogonService logonService) {
        super(repository);
        this.logonService = logonService;
    }

    public List<Client> findAllByUser() {
        try {
            return ((ClientJPARepository) repository)
                    .findByAppUser(logonService.getLoggedUser());
        } catch (Exception e) {
            LOGGER.error("Error in findAllByUser.", e);
            throw new BusinessException(e);
        }
    }

    @Override
    public Client save(Client entity) {
        entity.setAppUser(logonService.getLoggedUser());
        return super.save(entity);
    }

}