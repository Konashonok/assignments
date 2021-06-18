package com.akonashonok.assignment.medialib.mapper;

import com.akonashonok.assignment.medialib.dto.PersonDTO;
import com.akonashonok.assignment.medialib.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDTO(Person entity);

    Person toEntity(PersonDTO person);
}
