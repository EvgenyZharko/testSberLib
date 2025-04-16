package ru.zharko.sberlib.controller.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zharko.sberlib.controller.BookRentalController;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.service.BookRentalService;

@RestController
@RequiredArgsConstructor
@Validated
public class BookRentalControllerImpl implements BookRentalController {

    private final BookRentalService bookRentalService;

    @PostMapping("/load-book-rentals")
    public void loadBookRentals(@Valid @RequestBody KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations) {
        bookRentalService.loadBookRentals(kafkaMessageBookRentalMigrations);
    }
}
