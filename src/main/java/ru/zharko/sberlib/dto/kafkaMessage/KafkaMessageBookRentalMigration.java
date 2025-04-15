package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class KafkaMessageBookRentalMigration {
    @JsonProperty("data")
    private List<BookRentalMigration> bookRentalMigrations;
}
