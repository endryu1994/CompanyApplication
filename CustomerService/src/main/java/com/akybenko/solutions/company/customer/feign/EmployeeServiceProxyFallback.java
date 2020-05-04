package com.akybenko.solutions.company.customer.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

class EmployeeServiceProxyFallback implements EmployeeServiceProxy {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceProxyFallback.class);

    private final Throwable cause;

    EmployeeServiceProxyFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseEntity<Void> deleteAll(String customerId) {
        if (cause instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) cause;
            LOG.error("Error in deleteAll method. Error code: {}. Error message: {}",
                    ex.getStatus().value(), ex.getLocalizedMessage());
            throw new RuntimeException("Something wrong");
        }
        return null;
    }
}
