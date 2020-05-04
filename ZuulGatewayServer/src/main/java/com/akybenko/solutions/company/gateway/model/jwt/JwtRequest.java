package com.akybenko.solutions.company.gateway.model.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JwtRequest {

    @ApiModelProperty(notes = "Email of the user", example = "royjones@example.com", required = true)
    @NotNull(message = "Username cannot be null")
    private String username;
    @ApiModelProperty(notes = "Password of the user", example = "some_password", required = true)
    @NotNull(message = "Password cannot be null")
    private String password;
}
