package com.akybenko.solutions.company.employee.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", fallbackFactory = CustomerServiceFallbackFactory.class)
public interface CustomerServiceProxy {

    @GetMapping("/exists/{customerId}")
    ResponseEntity<Boolean> isCustomerExists(@PathVariable String customerId);
}
