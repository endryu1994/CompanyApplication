package com.akybenko.solutions.company.customer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@Data
public class Address {

    @ApiModelProperty(notes = "Street", example = "21 Royal Beach", required = true)
    @NotNull(message = "Street cannot be null")
    @Size(min = 2, message = "Street must not be less than 2 characters")
    private String street;
    @ApiModelProperty(notes = "City", example = "Los Angeles", required = true)
    @NotNull(message = "City cannot be null")
    @Size(min = 2, message = "City must not be less than 2 characters")
    private String city;
    @ApiModelProperty(notes = "Region", example = "California")
    @Size(min = 2, message = "Region must not be less than 2 characters")
    private String region;
    @ApiModelProperty(notes = "Country", example = "USA", required = true)
    @NotNull(message = "Country cannot be null")
    @Size(min = 2, message = "Country must not be less than 2 characters")
    private String country;
    @ApiModelProperty(notes = "Zipcode", example = "32578", required = true)
    @NotNull(message = "Zipcode cannot be null")
    @Size(min = 5, max = 5, message = "Zipcode must be equals 5 characters")
    private String zipcode;
}
