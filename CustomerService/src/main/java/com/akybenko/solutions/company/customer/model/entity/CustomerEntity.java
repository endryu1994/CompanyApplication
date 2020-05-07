package com.akybenko.solutions.company.customer.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
@Data
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = -8388205419779801524L;

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CUSTOMER_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1")
            }
    )
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
