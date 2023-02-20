package com.phonebook.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.phonebook.app.contact.DateTimeEnum;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by David on 19 Feb, 2023
 **/
@Data
public class ContactDto {

    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeEnum.DATE_TIME_PATTERN)
    private LocalDateTime createdAt;
}
