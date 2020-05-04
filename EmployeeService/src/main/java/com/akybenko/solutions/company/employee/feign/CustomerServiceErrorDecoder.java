package com.akybenko.solutions.company.employee.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CustomerServiceErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String method, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        return status.isError() ? new ResponseStatusException(status, response.reason()) : null;
    }
}
