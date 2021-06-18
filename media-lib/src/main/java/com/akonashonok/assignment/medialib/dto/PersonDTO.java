package com.akonashonok.assignment.medialib.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
public class PersonDTO {
    private long id;
    private String firstname;
    private String lastname;
    private String email;
}
