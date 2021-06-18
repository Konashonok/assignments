package com.akonashonok.assignment.medialib.controller;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import com.akonashonok.assignment.medialib.service.MediaLibService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/medias")
public class MediaLibController {

    private final MediaLibService mediaLibService;

    @GetMapping
    public Page<MediaItemDTO> findMediaItems(Pageable pageable){
        return mediaLibService.find(pageable);
    }

}
