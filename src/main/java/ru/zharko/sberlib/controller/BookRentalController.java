package ru.zharko.sberlib.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;

@RequestMapping("/books-rental")
public interface BookRentalController {
    void loadBookRentals(KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations);
}
