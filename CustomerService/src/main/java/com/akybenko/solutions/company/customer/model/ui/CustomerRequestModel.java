package com.akybenko.solutions.company.customer.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CustomerRequestModel {

    @ApiModelProperty(notes = "Name of the customer", example = "John Smith", required = true)
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must not be less than 2 characters")
    private String name;
    @ApiModelProperty(notes = "Description of the customer", example = "John Smith", required = true)
    @NotNull(message = "Description cannot be null")
    @Size(min = 8, message = "Description must not be less than 2 characters")
    private String description;
    @ApiModelProperty(notes = "Email of the customer", example = "johnsmith@example.com", required = true)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email address")
    private String email;
    @ApiModelProperty(notes = "Address of the customer", example = "32106, Florida, San Angeles, 888 Constantine Ave, #54", required = true)
    @NotNull(message = "Address cannot be null")
    private String address;
}
