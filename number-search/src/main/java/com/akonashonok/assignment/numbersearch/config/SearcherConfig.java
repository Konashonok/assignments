package com.akonashonok.assignment.numbersearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "searcher")
@Data
public class SearcherConfig {
    private long from;
    private long to;
    private int threads;
}
