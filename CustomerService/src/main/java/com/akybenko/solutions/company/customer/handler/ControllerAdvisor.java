package com.akybenko.solutions.company.customer.handler;

import com.akybenko.solutions.company.customer.exception.EntityNotFoundException;
import com.akybenko.solutions.company.customer.model.ErrorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerAdvisor.class);

    @SuppressWarnings("ConstantConditions")
    @ExceptionHandler({
            DataIntegrityViolationException.class
    })
    public ResponseEntity<Object> handleDataIntegrityViolationException(Exception e, WebRequest request) {
        ErrorModel body = body(e, request, HttpStatus.CONFLICT);
        return handleExceptionInternal(e, body, null, HttpStatus.CONFLICT, request);
    }

    @SuppressWarnings("ConstantConditions")
    @ExceptionHandler({
            IllegalArgumentException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<Object> handleEntityNotFoundException(Exception e, WebRequest request) {
        ErrorModel body = body(e, request, HttpStatus.NOT_FOUND);
        return handleExceptionInternal(e, body, null, HttpStatus.NOT_FOUND, request);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorModel body = body(e, request, HttpStatus.BAD_REQUEST);
        return handleExceptionInternal(e, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorModel body(Exception e, WebRequest request, HttpStatus status) {
        ErrorModel response = new ErrorModel();
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        if (e instanceof MethodArgumentNotValidException) {
            response.setErrors(fillErrors((MethodArgumentNotValidException) e));
            response.setMessage("Invalid object");
        } else {
            response.setMessage(e.getLocalizedMessage());
        }
        response.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        LOG.error("ControllerAdvisor: status: {}, object: {}, message: {}", status, response, e.getLocalizedMessage());
        return response;
    }

    private List<Map<String, String>> fillErrors(MethodArgumentNotValidException e) {
        List<Map<String, String>> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            Map<String, String> map = new HashMap<>();
            map.put("field", error.getField());
            map.put("message", error.getDefaultMessage());
            errors.add(map);
        });
        e.getBindingResult().getGlobalErrors().forEach(error -> {
            Map<String, String> map = new HashMap<>();
            map.put("object", error.getObjectName());
            map.put("message", error.getDefaultMessage());
            errors.add(map);
        });
        return errors;
    }
}
