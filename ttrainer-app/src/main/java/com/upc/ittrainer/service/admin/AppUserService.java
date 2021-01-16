package com.upc.ittrainer.service.admin;

import com.github.adminfaces.template.exception.BusinessException;
import com.upc.ittrainer.model.admin.AppUser;
import com.upc.ittrainer.repository.admin.AppUserJPARepository;
import com.upc.ittrainer.service.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@org.springframework.stereotype.Service
public class AppUserService extends Service<AppUser, String> {

    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserJPARepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void validateInsert(AppUser entity) {
        super.validateInsert(entity);

        if (!entity.getName().equals(entity.getName().toLowerCase())) {
            throw new BusinessException("User name must be lower case: " + entity.getID());
        }
    }

    @Override
    public AppUser save(AppUser entity) {
        updatePassword(entity);
        return super.save(entity);
    }

    public void updatePassword(AppUser entity) {
        final Optional<AppUser> optional;
        try {
            optional = getRepository().findById(entity.getID());
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        // New user
        if (!optional.isPresent()) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            return;
        }
        //Update user
        final AppUser oldEntity = optional.get();
        if (!entity.getPassword().equals(oldEntity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
    }

}