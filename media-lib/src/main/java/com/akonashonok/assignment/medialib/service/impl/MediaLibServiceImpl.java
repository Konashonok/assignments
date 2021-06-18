package com.akonashonok.assignment.medialib.service.impl;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import com.akonashonok.assignment.medialib.mapper.MediaItemMapper;
import com.akonashonok.assignment.medialib.repository.MediaItemRepository;
import com.akonashonok.assignment.medialib.service.MediaLibReader;
import com.akonashonok.assignment.medialib.service.MediaLibService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MediaLibServiceImpl implements MediaLibService {

    private final MediaLibReader mediaLibReader;
    private final MediaItemMapper mediaItemMapper;
    private final MediaItemRepository mediaItemRepository;

    @Override
    @Scheduled(fixedRate = 5000)
    public void ingest() {
        Collection<MediaItemDTO> mediaItems;
        try {
            mediaItems = mediaLibReader.read();
        } catch (IOException e) {
            log.warn("could not to read media items, reason + " + e.getMessage());
            return;
        }
        save(mediaItems);
    }

    @Override
    public Page<MediaItemDTO> find(Pageable pageable) {
        return mediaItemRepository.findAll(pageable)
                .map(mediaItemMapper::toDTO);
    }

    @Override
    public void save(Collection<MediaItemDTO> mediaItems) {
        mediaItemRepository.saveAll(mediaItems.stream()
                .map(mediaItemMapper::toEntity)
                .collect(Collectors.toList()));
    }
}
