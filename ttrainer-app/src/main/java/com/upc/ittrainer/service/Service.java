package com.upc.ittrainer.service;

import com.github.adminfaces.template.exception.BusinessException;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.reflect.TypeToken;
import com.upc.ittrainer.model.ModelEntity;
import com.upc.ittrainer.util.FieldValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class Service<T extends ModelEntity<ID>, ID extends Comparable> {

    private static final Logger LOGGER = LogManager.getLogger();
    @Getter
    protected final JpaRepository<T, ID> repository;
    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
    };
    private final TypeToken<ID> idTypeToken = new TypeToken<ID>(getClass()) {
    };

    public T insert(T entity) {
        validateInsert(entity);
        return save(entity);
    }

    protected void validateInsert(T entity) {
        validate(entity);

        if (exists(entity)) {
            throw new BusinessException("Duplicate entity id: " + entity.getID());
        }
    }

    protected T save(T entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            LOGGER.error("Error saving entity", e);
            throw new BusinessException(e.getLocalizedMessage());
        }
    }

    private void validate(T entity) {
        FieldValidator.validateEntity(entity);
    }

    public void remove(T entity) {
        try {
            repository.delete(entity);
        } catch (Exception e) {
            LOGGER.error("Error deleting entity", e);
            throw new BusinessException(e.getLocalizedMessage());
        }
    }

    public T update(T entity) {
        validate(entity);
        return save(entity);
    }

    public T findById(ID id) {
        final Optional<T> entity;
        try {
            entity = repository.findById(id);
        } catch (Exception ex) {
            throw new BusinessException(ex);
        }
        if (!entity.isPresent()) {
            throw new BusinessException("Entity not found for id: " + id);
        }
        return entity.get();

    }

    public synchronized List<T> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error in findAll.", e);
            throw new BusinessException(e);
        }
    }

    public T newEntityInstance() {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BusinessException("Error getting instance of entity.", e.getLocalizedMessage());
        }
    }

    public final Class<? extends T> getEntityClass() {
        return (Class<T>) typeToken.getRawType();
    }

    public final Class<? extends ID> getIdClass() {
        return (Class<ID>) idTypeToken.getRawType();
    }

    private boolean exists(T entity) {
        if (entity.isNew()) {
            return false;
        }

        try {
            return repository.existsById(entity.getID());
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

}