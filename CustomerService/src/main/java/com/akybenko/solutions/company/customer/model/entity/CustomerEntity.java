package com.akybenko.solutions.company.customer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
@Data
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 6212877011075886359L;

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String customerId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 50)
    private String description;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false, length = 120)
    private String address;
}
