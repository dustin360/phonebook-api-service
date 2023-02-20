package com.phonebook.app.controller;

import com.phonebook.app.contact.ApiRoute;
import com.phonebook.app.dto.ContactDto;
import com.phonebook.app.dto.GeneralResponse;
import com.phonebook.app.dto.request.ContactCreationRequest;
import com.phonebook.app.model.Contact;
import com.phonebook.app.service.ContactService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 19 Feb, 2023
 **/
@RestController
@RequestMapping(ApiRoute.CONTACTS)
@AllArgsConstructor
public class ContractController {

    private ContactService contactService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody @Valid ContactCreationRequest request) {

        Contact c = contactService.create(request);

        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.CREATED.value(),
                        "Contact successfully registered",
                        modelMapper.map(c, ContactDto.class)),
                HttpStatus.CREATED);
    }

    @GetMapping(ApiRoute.ALL)
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "50") int limit) {

        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.OK.value(),
                        "", contactService.getAll(page, limit)),
                HttpStatus.OK);
    }

    @GetMapping("/{phone-number}")
    public ResponseEntity<?> getOne(@PathVariable("phone-number") String phoneNumber) {

        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.OK.value(),
                        "", contactService.getOne(phoneNumber)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{phone-number}")
    public ResponseEntity<?> delete(@PathVariable("phone-number") String phoneNumber) {

        contactService.delete(phoneNumber);

        return new ResponseEntity<>(
                new GeneralResponse(HttpStatus.OK.value(),
                        "Contact deleted"),
                HttpStatus.OK);
    }

}
