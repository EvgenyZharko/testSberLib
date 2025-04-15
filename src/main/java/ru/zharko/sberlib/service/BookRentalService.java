package ru.zharko.sberlib.service;

import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;

import java.util.List;

public interface BookRentalService {
    void pushToTopic(KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations);
    void prepareBookRentalListFromKafka(List<BookRentalMigration> rentals);
}
