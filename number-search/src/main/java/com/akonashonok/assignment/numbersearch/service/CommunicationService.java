package com.akonashonok.assignment.numbersearch.service;

import com.akonashonok.assignment.numbersearch.config.SearcherConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@AllArgsConstructor
@Slf4j
public class CommunicationService {

    private final SearcherConfig searcherConfig;
    private final Scanner scanner;

    public long getNumber() {
        long number = searcherConfig.getFrom() - 1;
        do {
            log.info("Enter a number between {} and {}", searcherConfig.getFrom(), searcherConfig.getTo());
            String input = scanner.nextLine();
            try {
                number = Long.parseLong(input);
            } catch (NumberFormatException e){
                log.warn("{} is not a number", input);
            }
        } while (number < searcherConfig.getFrom() || number > searcherConfig.getTo());
        return number;
    }

    public boolean repeat(){
        log.info("Repeat again? y/n");
        String input = scanner.nextLine();
        return "y".equalsIgnoreCase(input) || "yes".equalsIgnoreCase(input);
    }
}
