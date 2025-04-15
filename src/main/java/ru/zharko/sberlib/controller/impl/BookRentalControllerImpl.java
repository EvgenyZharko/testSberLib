package ru.zharko.sberlib.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zharko.sberlib.controller.BookRentalController;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.service.BookRentalService;

@RestController
@RequiredArgsConstructor
public class BookRentalControllerImpl implements BookRentalController {

    private final BookRentalService bookRentalService;

    @PostMapping("/push-to-kafka")
    public void pushToTopic(@RequestBody KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations) {
        bookRentalService.pushToTopic(kafkaMessageBookRentalMigrations);
    }
}
