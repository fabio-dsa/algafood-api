package com.algafood.api.exception;

import com.algafood.api.exception.model.Error;
import com.algafood.api.exception.model.ErrorType;
import com.algafood.api.exception.model.ValidationFailure;
import com.algafood.core.util.MessageHelper;
import com.algafood.domain.exception.BusinessException;
import com.algafood.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageHelper messageHelper;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return handleError(ex, ex.getBindingResult(), ErrorType.DADOS_INVALIDOS, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return handleError(ex, ErrorType.ERRO_NEGOCIO, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return handleError(ex, ErrorType.RECURSO_NAO_ENCONTRADO, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {
        return handleError(ex, ErrorType.ERRO_INTERNO, request);
    }

    private ResponseEntity<Object> handleError(Exception ex, ErrorType errorType, WebRequest request) {
        Error error = new Error.Builder()
                .errorType(errorType)
                .detail(ex.getMessage())
                .build();
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.valueOf(error.getStatus()), request);
    }

    private ResponseEntity<Object> handleError(Exception ex, BindingResult bindingResult, ErrorType errorType, WebRequest request) {
        List<ValidationFailure> validationFailures = bindingResult.getAllErrors().stream().map(objectError -> {
            var message = messageHelper.getMessage(objectError);
            var field = objectError instanceof FieldError ? ((FieldError) objectError).getField() : objectError.getObjectName();

            return ValidationFailure.builder()
                    .field(field)
                    .message(message)
                    .build();

        }).collect(Collectors.toList());

        Error error = new Error.Builder()
                .errorType(errorType)
                .validationFailures(validationFailures)
                .build();

        return super.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.valueOf(error.getStatus()), request);
    }

}
