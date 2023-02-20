package com.phonebook.app.service;

import com.phonebook.app.dto.request.ContactCreationRequest;
import com.phonebook.app.exception.BadRequestException;
import com.phonebook.app.model.Contact;
import com.phonebook.app.repo.ContactRepo;
import com.phonebook.app.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by David on 20 Feb, 2023
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ContactServiceImpl.class, ModelMapper.class})
public class ContactServiceImplTest {

    @MockBean
    private ContactRepo contactRepo;

    @Autowired
    private ContactService contactService;

    @Captor
    private ArgumentCaptor<Contact> contactArgumentCaptor;


    @Test
    void testCreateContact_throwsException() {

        ContactCreationRequest req = new ContactCreationRequest();
        req.setName("Jerry");
        req.setPhoneNumber("08099223344");
        req.setAddress("13 green lane");

        when(contactRepo.existsByPhoneNumber(any())).thenReturn(true);

        assertThrows(BadRequestException.class, ()->
                contactService.create(req), "Phone number already exists");

    }

    @Test
    void testCreateContact() {

        ContactCreationRequest req = new ContactCreationRequest();
        req.setName("Jerry");
        req.setPhoneNumber("08099223344");
        req.setAddress("13 green lane");

        when(contactRepo.existsByPhoneNumber(any())).thenReturn(false);

        contactService.create(req);
        verify(contactRepo).save(contactArgumentCaptor.capture());

        Contact contact = contactArgumentCaptor.getValue();
        assertThat(req).isEqualToComparingFieldByField(contact);
    }

    @Test
    void testGetOneContact() {

        Contact c = new Contact();
        c.setName("Jerry");
        c.setPhoneNumber("08099223344");
        c.setAddress("13 green lane");

        when(contactRepo.findByPhoneNumber(any())).thenReturn(Optional.of(c));

        Contact result = contactService.getOne("08099223344");

        assertThat(result).isEqualToComparingFieldByField(c);
    }

    @Test
    void testDeleteOneContact() {

        Contact c = new Contact();
        c.setName("Jerry");
        c.setPhoneNumber("08099223344");
        c.setAddress("13 green lane");

        when(contactRepo.findByPhoneNumber(any())).thenReturn(Optional.of(c));

        contactService.delete("08099223344");
        verify(contactRepo, times(1)).save(any(Contact.class));
    }
}
