package com.akybenko.solutions.company.employee.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = -2274173137782261358L;

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "EMPLOYEE_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1")
            }
    )
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 50)
    private String customerId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
}
