package com.akybenko.solutions.company.gateway.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateUserResponseModel {

    @ApiModelProperty(notes = "First name of the user", example = "Roy", required = true)
    private String firstName;
    @ApiModelProperty(notes = "Last name of the user", example = "Jones", required = true)
    private String lastName;
    @ApiModelProperty(notes = "Email of the employee", example = "royjones@example.com", required = true)
    private String email;
    @ApiModelProperty(notes = "Password of the user", example = "some_password", required = true)
    private String password;
}
