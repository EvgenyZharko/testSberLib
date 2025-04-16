package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
    @Valid
    @NotEmpty(message = "Список книг не может быть пустым")
    private List<BookRentalMigration> bookRentalMigrations;

}
