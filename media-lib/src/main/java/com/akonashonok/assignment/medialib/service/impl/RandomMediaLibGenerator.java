package com.akonashonok.assignment.medialib.service.impl;

import com.akonashonok.assignment.medialib.dto.MediaItemDTO;
import com.akonashonok.assignment.medialib.dto.PersonDTO;
import com.akonashonok.assignment.medialib.service.MediaLibGenerator;
import com.akonashonok.assignment.medialib.service.MediaLibWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class RandomMediaLibGenerator implements MediaLibGenerator {

    private static final Set<String> FIRST_NAMES = Set.of("Isis", "Ryley", "Orlaith", "Koa", "Janet",
            "Emilis", "Elena", "Usamah", "Mehmet", "Rocco");
    private static final Set<String> LAST_NAMES = Set.of("Rennie", "Greer", "Avalos", "Rudd", "Sierra",
            "Blankenship", "Byers", "Ramirez", "Schwartz", "Mohamed");
    private static final Set<String> MEDIA_NAMES = Set.of("The Shawshank Redemption ", "The Godfather", "The Dark Knight",
            "Angry Men", "Schindler's List", "The Lord of the Rings: The Return of the King",
            "Pulp Fiction", "The Good, the Bad and the Ugly", "The Lord of the Rings: The Fellowship of the Ring",
            "Fight Club");

    private final MediaLibWriter mediaLibWriter;

    @Override
    @Scheduled(fixedRate = 5000)
    public void generate() {
        Collection<MediaItemDTO> items = generate((int) (10 * Math.random()));
        try {
            mediaLibWriter.write(items);
        } catch (IOException e) {
            log.warn("could not write data, reason: {}", e.getMessage());
        }
    }

    private Collection<MediaItemDTO> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i->{
                    MediaItemDTO mediaItem = generateMediaItem();
                    PersonDTO person = generatePerson();
                    mediaItem.setOwner(person);
                    return mediaItem;
                })
                .collect(Collectors.toList());

    }

    private MediaItemDTO generateMediaItem(){
        MediaItemDTO mediaItem = new MediaItemDTO();
        MEDIA_NAMES.stream()
                .skip((int) (MEDIA_NAMES.size() * Math.random()))
                .findAny()
                .ifPresent(mediaItem::setName);
        long minDay = LocalDate.of(2000, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2021, 6, 1).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate releaseDate = LocalDate.ofEpochDay(randomDay);
        mediaItem.setReleaseDate(releaseDate);
        return mediaItem;
    }

    private PersonDTO generatePerson(){
        PersonDTO person = new PersonDTO();
        FIRST_NAMES.stream()
                .skip((int) (FIRST_NAMES.size() * Math.random()))
                .findAny()
                .ifPresent(person::setFirstname);
        LAST_NAMES.stream()
                .skip((int) (LAST_NAMES.size() * Math.random()))
                .findAny()
                .ifPresent(person::setLastname);
        person.setEmail(RandomStringUtils.randomAlphabetic((int) (5 + 7 * Math.random())) +
                "@" +
                RandomStringUtils.randomAlphabetic((int) (3 + 5 * Math.random())) +
                ".com");
        return person;
    }


}
