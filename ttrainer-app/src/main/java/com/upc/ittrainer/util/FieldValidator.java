package com.upc.ittrainer.util;

import com.github.adminfaces.template.exception.BusinessException;
import com.google.common.base.Strings;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;

import static com.github.adminfaces.template.util.Assert.has;

public class FieldValidator {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
    private static final Validator VALIDATOR = VALIDATOR_FACTORY.getValidator();

    private FieldValidator() {
    }

    private static void validateEmptyFields(final Map<String, Object> fields) {
        BusinessException be = new BusinessException();
        fields.forEach((key, value) -> {
            if ((value instanceof String && validateEmpty((String) value)) || validateEmpty(value)) {
                be.addException(new BusinessException(key + " cannot be empty"));
            }
        });

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

    private static boolean validateEmpty(final String fieldValue) {
        return Strings.isNullOrEmpty(fieldValue);
    }

    private static boolean validateEmpty(final Object fieldValue) {
        return fieldValue == null;
    }

    public static <T> void validateEntity(final T entity) {
        Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(entity);
        final BusinessException be = new BusinessException();
        constraintViolations
                .forEach(c -> be.addException(new BusinessException(c.getPropertyPath() + " " + c.getMessage())));

        if (has(be.getExceptionList())) {
            throw be;
        }
    }

}
