package com.akonashonok.assignment.medialib.service.impl;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import com.akonashonok.assignment.medialib.service.MediaLibReader;
import com.akonashonok.assignment.medialib.service.MediaLibWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.akonashonok.assignment.medialib.config.MediaLibConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class JsonMediaLibIO implements MediaLibReader, MediaLibWriter {

    public static final String FILENAME_FORMAT = "lib_%s.json";
    private final MediaLibConfig config;
    private final ObjectMapper objectMapper;

    @Override
    public Collection<MediaItemDTO> read() {
        File directory = new File(config.getDirectory());
        if (!directory.exists() || !directory.isDirectory()) {
            log.warn("file {} does not exists or it's not directory", directory.getPath());
            return Collections.emptyList();
        }
        File[] dataFiles = directory.listFiles(getFileNameFilter());
        if (dataFiles == null || dataFiles.length == 0) {
            return Collections.emptyList();
        }
        try {
            return doRead(dataFiles);
        } finally {
            for (File dataFile : dataFiles) {
                FileUtils.deleteQuietly(dataFile);
            }
        }
    }

    private List<MediaItemDTO> doRead(File[] dataFiles) {
        return Arrays.stream(dataFiles)
                .map(file -> {
                    try {
                        return objectMapper.readValue(file, MediaItemDTO[].class);
                    } catch (Exception e) {
                        log.warn("could not parse file {}, reason {}", file.getAbsolutePath(), e.getMessage());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void write(Collection<MediaItemDTO> items) throws IOException {
        File file = new File(config.getDirectory(), getFilename(UUID.randomUUID().toString()));
        objectMapper.writeValue(new FileOutputStream(file, false), items);
    }

    protected FilenameFilter getFileNameFilter() {
        return new WildcardFileFilter(getFilename("*"));
    }

    protected String getFilename(String postfix) {
        return String.format(FILENAME_FORMAT, postfix);
    }
}
