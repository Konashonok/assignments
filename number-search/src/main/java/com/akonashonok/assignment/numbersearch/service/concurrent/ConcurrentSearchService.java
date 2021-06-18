package com.akonashonok.assignment.numbersearch.service.concurrent;

import com.akonashonok.assignment.numbersearch.service.SearchService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Profile("concurrent")
@Primary
@AllArgsConstructor
public class ConcurrentSearchService implements SearchService {

    private final SearcherFactory searcherFactory;
    private final ExecutorService executorService;

    @SneakyThrows
    @Override
    public void search(long numberToFind) {
        Coordinator coordinator = new Coordinator();
        Collection<Searcher> searchers = searcherFactory.create(numberToFind, coordinator);
        long startTime = System.nanoTime();
        List<Future<Long>> futures = executorService.invokeAll(searchers);
        for (Future<Long> future : futures) {
            future.get();
        }
        long duration = System.nanoTime() - startTime;
        log.info("Threads summary:");
        coordinator.getIterationPerThread().forEach((k, v)-> log.info("Thread '{}': {} iterations", k, v));
        log.info("Your number  is intersected after {} iterations, in {} ms, thread: '{}'",
                coordinator.getTotalIterations(),
                TimeUnit.NANOSECONDS.toMillis(duration),
                coordinator.getSuccessfulThread());

    }
}
