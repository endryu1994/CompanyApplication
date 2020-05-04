package com.akybenko.solutions.company.employee.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

class CustomerServiceProxyFallback implements CustomerServiceProxy {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceProxyFallback.class);

    private final Throwable cause;

    CustomerServiceProxyFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ResponseEntity<Boolean> isCustomerExists(String customerId) {
        if (cause instanceof ResponseStatusException) {
            ResponseStatusException ex = (ResponseStatusException) cause;
            LOG.error("Error in isCustomerExists method. Error code: {}. Error message: {}",
                    ex.getStatus().value(), ex.getLocalizedMessage());
        }
        return ResponseEntity.ok(false);
    }
}
