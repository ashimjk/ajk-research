package io.ashimjk.testing.web;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@NoArgsConstructor
public class ErrorResult {

    private final List<FieldValidationError> fieldErrors = new ArrayList<>();

    ErrorResult(String field, String message) {
        this.fieldErrors.add(new FieldValidationError(field, message));
    }

}