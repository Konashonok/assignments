package com.akonashonok.assignment.numbersearch.service.concurrent;

import java.util.HashMap;
import java.util.Map;


public class Coordinator {

    private final Map<String, Long> iterationsPerThread = new HashMap<>();
    private boolean numberFound;
    private String successfulThread;

    public synchronized void onNumberFound() {
        this.successfulThread = Thread.currentThread().getName();
        this.numberFound = true;
    }

    public synchronized void addIterationsForCurrentThread(long iterationToAdd){
        String name = Thread.currentThread().getName();
        Long iterations = iterationsPerThread.getOrDefault(name, 0L);
        iterations+=iterationToAdd;
        this.iterationsPerThread.put(name, iterations);
    }

    public boolean isNumberFound() {
        return numberFound;
    }

    public String getSuccessfulThread() {
        return successfulThread;
    }

    public Map<String, Long> getIterationPerThread(){
        return new HashMap<>(iterationsPerThread);
    }

    public long getTotalIterations(){
        return iterationsPerThread.values().stream()
                .mapToLong(l->l)
                .sum();
    }




}
