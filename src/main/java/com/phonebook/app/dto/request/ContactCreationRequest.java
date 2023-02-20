package com.phonebook.app.dto.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * Created by David on 19 Feb, 2023
 **/
@Data
public class ContactCreationRequest {

    @NotBlank(message = "Contact name is required")
    private String name;

    @NotBlank(message = "Contact phone number is required")
    private String phoneNumber;

    private String address;
}
