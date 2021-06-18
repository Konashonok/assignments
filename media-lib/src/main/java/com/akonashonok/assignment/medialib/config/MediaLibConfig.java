package com.akonashonok.assignment.medialib.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "media-lib")
@Data
public class MediaLibConfig {
    private String directory;
}
