package com.akonashonok.assignment.medialib.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MediaItemDTO {
    private Long id;
    private String name;
    private LocalDate releaseDate;
    private PersonDTO owner;
}
