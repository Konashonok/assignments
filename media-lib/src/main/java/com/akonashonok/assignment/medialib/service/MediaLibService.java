package com.akonashonok.assignment.medialib.service;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface MediaLibService {

    void ingest();

    Page<MediaItemDTO> find(Pageable pageable);

    void save(Collection<MediaItemDTO> mediaItems);
}
