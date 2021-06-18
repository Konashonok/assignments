package com.akonashonok.assignment.numbersearch.service.concurrent;

import com.akonashonok.assignment.numbersearch.config.SearcherConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Slf4j
@Component
@AllArgsConstructor
public class SearcherFactory {

    private final SearcherConfig searcherConfig;

    public Collection<Searcher> create(long numberToFind, Coordinator coordinator) {
        long total = searcherConfig.getTo() - searcherConfig.getFrom();
        int intervals = searcherConfig.getThreads();
        long intervalSize = total / intervals;
        return IntStream.range(0, intervals)
                .mapToObj(i -> {
                    long intervalFrom = intervalSize * i;
                    long intervalTo = i + 1 == intervals ? searcherConfig.getTo() : intervalFrom + intervalSize;
                    return create(intervalFrom, intervalTo, numberToFind, coordinator);
                })
                .collect(Collectors.toList());
    }

    public Searcher create(long from, long to, long numberToFind, Coordinator coordinator) {
        return new Searcher(from, to, numberToFind, coordinator);
    }


}
