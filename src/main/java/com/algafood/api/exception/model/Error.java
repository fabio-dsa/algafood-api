package com.algafood.api.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Integer status;

    private String title;

    private String detail;

    private List<ValidationFailure> validationFailures;

    private LocalDateTime timestamp;


    public static class Builder {
        private final Error error;

        public Builder() {
            error = new Error();
            error.timestamp = LocalDateTime.now();
        }

        public Builder errorType(ErrorType errorType) {
            this.error.title = errorType.getDescricao();
            this.error.status = errorType.getStatusHttp().value();
            return this;
        }

        public Builder detail(String detail) {
            this.error.detail = detail;
            return this;
        }

        public Builder validationFailures(List<ValidationFailure> validationFailures) {
            this.error.validationFailures = validationFailures;
            return this;
        }

        public Error build() {
            return error;
        }
    }
}
