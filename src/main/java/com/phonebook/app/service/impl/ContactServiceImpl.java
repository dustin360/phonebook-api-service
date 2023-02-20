package com.phonebook.app.service.impl;

import com.phonebook.app.dto.ContactDto;
import com.phonebook.app.dto.PaginatedListDto;
import com.phonebook.app.dto.request.ContactCreationRequest;
import com.phonebook.app.exception.BadRequestException;
import com.phonebook.app.exception.NotFoundException;
import com.phonebook.app.model.Contact;
import com.phonebook.app.repo.ContactRepo;
import com.phonebook.app.service.ContactService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David on 19 Feb, 2023
 **/
@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private ContactRepo contactRepo;

    private ModelMapper modelMapper;

    @Override
    public Contact create(ContactCreationRequest request) {

        if (contactRepo.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new BadRequestException("Phone number already exists");
        }

        Contact contact = new Contact();
        modelMapper.map(request, contact);

        return contactRepo.save(contact);
    }

    @Override
    public PaginatedListDto<ContactDto> getAll(int page, int limit) {

        Page<Contact> pageInfo = contactRepo.findAll(PageRequest.of(page, limit));

        List<ContactDto> contactList = pageInfo.stream()
                .map(c -> modelMapper.map(c, ContactDto.class))
                .collect(Collectors.toList());

        return new PaginatedListDto<>(page, limit, pageInfo.getTotalElements(), contactList);
    }

    @Override
    public Contact getOne(String phoneNumber) {
        return contactRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException("Contact details not found"));
    }

    @Override
    public void delete(String phoneNumber) {
        Contact contact = contactRepo.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException("Contact details not found"));

        contact.setDeleted(true);
        contactRepo.save(contact);
    }
}
