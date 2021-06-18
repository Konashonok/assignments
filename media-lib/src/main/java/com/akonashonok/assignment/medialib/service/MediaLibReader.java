package com.akonashonok.assignment.medialib.service;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;

import java.io.IOException;
import java.util.Collection;

public interface MediaLibReader {

    Collection<MediaItemDTO> read() throws IOException;
}
