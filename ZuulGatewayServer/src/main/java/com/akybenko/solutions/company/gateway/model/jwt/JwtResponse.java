package com.akybenko.solutions.company.gateway.model.jwt;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    @ApiModelProperty(notes = "Authorization token", example = "some_jwt_token", required = true)
    private String token;
}
