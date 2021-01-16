package com.upc.ittrainer.service.progression;

import com.upc.ittrainer.model.progression.Scope;
import com.upc.ittrainer.repository.progression.ScopeJPARepository;
import com.upc.ittrainer.service.Service;

@org.springframework.stereotype.Service
public class ScopeService extends Service<Scope, String> {

    public ScopeService(ScopeJPARepository repository) {
        super(repository);
    }
}