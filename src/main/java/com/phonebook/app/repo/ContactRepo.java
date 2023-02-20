package com.phonebook.app.repo;

import com.phonebook.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by David on 19 Feb, 2023
 **/
public interface ContactRepo extends JpaRepository<Contact, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
