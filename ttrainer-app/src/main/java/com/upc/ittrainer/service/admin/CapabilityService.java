/*
 * CapabilityService.java
 */
package com.upc.ittrainer.service.admin;

import com.upc.ittrainer.model.admin.Capability;
import com.upc.ittrainer.repository.admin.CapabilityJPARepository;
import com.upc.ittrainer.service.Service;

@org.springframework.stereotype.Service
public class CapabilityService extends Service<Capability, String> {

    public CapabilityService(CapabilityJPARepository repository) {
        super(repository);
    }

}
