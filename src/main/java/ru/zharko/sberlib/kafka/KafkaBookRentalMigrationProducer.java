package ru.zharko.sberlib.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;

@Slf4j
@Service
public class KafkaBookRentalMigrationProducer {

    @Value("${spring.kafka.topic.book-rental-migration}")
    private String topicName;

    private final KafkaTemplate<String, BookRentalMigration> kafkaTemplate;

    public KafkaBookRentalMigrationProducer(KafkaTemplate<String, BookRentalMigration> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookRentalToTopic(BookRentalMigration rent) {
        kafkaTemplate.send(topicName, rent);
    }
}
