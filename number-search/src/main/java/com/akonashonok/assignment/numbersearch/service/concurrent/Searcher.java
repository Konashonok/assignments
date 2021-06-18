package com.akonashonok.assignment.numbersearch.service.concurrent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@AllArgsConstructor
@Getter
@Slf4j
public class Searcher implements Callable<Long> {
    private final long from;
    private final long to;
    private final long number;
    private final Coordinator coordinator;

    @Override
    public Long call() {
        long iterations = 0;
        log.debug("start searching in range {} - {}", from, to);
        for (long i = from; i < to; i++) {
            iterations++;
            if (coordinator.isNumberFound()) {
                log.debug("stop searching, number found by another thread ({}),  iterations: {}", coordinator.getSuccessfulThread(), iterations);
                break;
            }
            if (i == number) {
                log.debug("number is found, iterations: {}", iterations);
                coordinator.onNumberFound();
                break;
            }
        }
        coordinator.addIterationsForCurrentThread(iterations);
        return iterations;
    }
}
