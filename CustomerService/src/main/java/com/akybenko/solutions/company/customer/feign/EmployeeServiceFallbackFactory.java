package com.akybenko.solutions.company.customer.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceFallbackFactory implements FallbackFactory<EmployeeServiceProxy> {

    @Override
    public EmployeeServiceProxy create(Throwable cause) {
        return new EmployeeServiceProxyFallback(cause);
    }
}
