package com.phonebook.app.service;

import com.phonebook.app.dto.ContactDto;
import com.phonebook.app.dto.PaginatedListDto;
import com.phonebook.app.dto.request.ContactCreationRequest;
import com.phonebook.app.model.Contact;

/**
 * Created by David on 19 Feb, 2023
 **/
public interface ContactService {

    Contact create(ContactCreationRequest request);

    PaginatedListDto<ContactDto> getAll(int page, int limit);

    Contact getOne(String phoneNumber);

    void delete(String phoneNumber);
}
