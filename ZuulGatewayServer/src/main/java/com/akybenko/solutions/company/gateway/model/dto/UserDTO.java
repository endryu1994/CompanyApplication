package com.akybenko.solutions.company.gateway.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private String email;
}
