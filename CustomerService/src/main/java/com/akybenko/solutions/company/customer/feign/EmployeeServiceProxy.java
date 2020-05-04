package com.akybenko.solutions.company.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "employee-service", fallbackFactory = EmployeeServiceFallbackFactory.class)
public interface EmployeeServiceProxy {

    @DeleteMapping("/delete")
    ResponseEntity<Void> deleteAll(@RequestParam String customerId);
}
