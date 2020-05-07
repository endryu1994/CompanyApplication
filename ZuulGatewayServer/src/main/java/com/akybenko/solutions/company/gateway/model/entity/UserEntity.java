package com.akybenko.solutions.company.gateway.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 9023553836568276807L;

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @GenericGenerator(
            name = "ID_GENERATOR",
            strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "USER_SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1")
            }
    )
    private Long id;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
}
