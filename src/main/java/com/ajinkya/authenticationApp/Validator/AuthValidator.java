package com.ajinkya.authenticationApp.Validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class AuthValidator {
    public List<String> validate(Object request) {
        List<String>errors = new ArrayList<>();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        for (ConstraintViolation<Object> violation : constraintViolations) {
            errors.add(violation.getMessage());
        }
        return errors;
    }
}
