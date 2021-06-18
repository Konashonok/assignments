package com.akonashonok.assignment.numbersearch;

import com.akonashonok.assignment.numbersearch.service.CommunicationService;
import com.akonashonok.assignment.numbersearch.service.SearchService;
import com.akonashonok.assignment.numbersearch.config.SearcherConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class NumberSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumberSearchApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SearchService searchService,
                                        CommunicationService communicationService,
                                        ExecutorService executorService) {
        return args -> {
            try {
                do {
                    long number = communicationService.getNumber();
                    searchService.search(number);
                }
                while (communicationService.repeat());
            }
            finally {
                executorService.shutdown();
            }
        };
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    ExecutorService executorService(SearcherConfig searcherConfig) {
        return Executors.newFixedThreadPool(searcherConfig.getThreads());
    }

}
