package com.akybenko.solutions.company.customer.model.ui;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerResponseModel {

    @ApiModelProperty(notes = "ID of the customer", example = "123e4567-e89b-12d3-a456-426655440000")
    private String customerId;
    @ApiModelProperty(notes = "Name of the customer", example = "John Smith")
    private String name;
    @ApiModelProperty(notes = "Description of the customer", example = "John Smith")
    private String description;
    @ApiModelProperty(notes = "Email of the customer", example = "johnsmith@example.com")
    private String email;
    @ApiModelProperty(notes = "Address of the customer", example = "32106, Florida, San Angeles, 888 Constantine Ave, #54")
    private String address;
}
