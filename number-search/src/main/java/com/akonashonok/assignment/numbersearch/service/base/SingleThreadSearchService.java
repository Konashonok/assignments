package com.akonashonok.assignment.numbersearch.service.base;

import com.akonashonok.assignment.numbersearch.config.SearcherConfig;
import com.akonashonok.assignment.numbersearch.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class SingleThreadSearchService implements SearchService {

    public final SearcherConfig searcherConfig;

    @Override
    public void search(long numberToFind) {
        long startTime = System.nanoTime();
        long iterations = 0;
        for (long i = searcherConfig.getFrom(); i < searcherConfig.getTo(); i++) {
            iterations++;
            if (i == numberToFind) {
                break;
            }
        }
        long duration = System.nanoTime() - startTime;
        log.info("Your number is intersected after {} iterations, in {} ms", iterations, TimeUnit.NANOSECONDS.toMillis(duration));
    }
}
