package com.algafood.api.exception.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationFailure {
    private String field;

    private String message;
}
