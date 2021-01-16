/*
 * RoleService.java
 */
package com.upc.ittrainer.service.admin;

import com.upc.ittrainer.model.admin.Role;
import com.upc.ittrainer.repository.admin.RoleJPARepository;
import com.upc.ittrainer.service.Service;

@org.springframework.stereotype.Service
public class RoleService extends Service<Role, String> {

    public RoleService(RoleJPARepository repository) {
        super(repository);
    }

}
