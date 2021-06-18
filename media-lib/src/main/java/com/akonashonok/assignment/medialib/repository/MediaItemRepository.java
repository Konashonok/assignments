package com.akonashonok.assignment.medialib.repository;

import com.akonashonok.assignment.medialib.model.MediaItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends PagingAndSortingRepository<MediaItem, Long> {

}
