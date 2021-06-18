package com.akonashonok.assignment.medialib.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    @OneToMany(mappedBy = "owner")
    private Set<MediaItem> items;
}
