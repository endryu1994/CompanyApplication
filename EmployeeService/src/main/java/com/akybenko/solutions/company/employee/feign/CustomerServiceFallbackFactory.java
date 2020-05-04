package com.akybenko.solutions.company.employee.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceFallbackFactory implements FallbackFactory<CustomerServiceProxy> {

    @Override
    public CustomerServiceProxy create(Throwable cause) {
        return new CustomerServiceProxyFallback(cause);
    }
}
