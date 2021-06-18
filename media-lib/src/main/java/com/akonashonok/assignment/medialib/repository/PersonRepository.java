package com.akonashonok.assignment.medialib.repository;

import com.akonashonok.assignment.medialib.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

}
