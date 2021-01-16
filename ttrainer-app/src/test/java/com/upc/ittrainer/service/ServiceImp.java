package com.upc.ittrainer.service;

import com.upc.ittrainer.model.ModelEntityImp;
import com.upc.ittrainer.repository.ModelEntityImpJPARepository;

public class ServiceImp extends Service<ModelEntityImp, Integer> {

    public ServiceImp(ModelEntityImpJPARepository repository) {
        super(repository);
    }
}
