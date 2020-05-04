package com.akybenko.solutions.company.gateway.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {

    @ApiModelProperty(notes = "First name of the user", example = "Roy", required = true)
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;
    @ApiModelProperty(notes = "Last name of the user", example = "Jones", required = true)
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;
    @ApiModelProperty(notes = "Password of the user", example = "some_password", required = true)
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be equal or grater than 8 characters and less than 16 characters")
    private String password;
    @ApiModelProperty(notes = "Email of the employee", example = "royjones@example.com", required = true)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email address")
    private String email;
}
