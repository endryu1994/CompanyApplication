package com.akybenko.solutions.company.customer.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class AddressEntity {

    @Column(nullable = false, length = 50)
    private String street;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(length = 50)
    private String region;
    @Column(nullable = false, length = 50)
    private String country;
    @Column(nullable = false, length = 5)
    private String zipcode;
}
