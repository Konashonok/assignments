package com.akonashonok.assignment.medialib.mapper;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import com.akonashonok.assignment.medialib.model.MediaItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface MediaItemMapper {

    MediaItemDTO toDTO(MediaItem entity);

    MediaItem toEntity(MediaItemDTO mediaItemDTO);
}
