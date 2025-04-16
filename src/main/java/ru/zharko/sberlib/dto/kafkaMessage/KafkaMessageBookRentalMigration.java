package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Schema(description = "Сообщение с пакетом данных о прокате книг")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class KafkaMessageBookRentalMigration {

    @ArraySchema(
            schema = @Schema(
                    description = "Элемент данных о прокате",
                    implementation = BookRentalMigration.class,
                    requiredMode = Schema.RequiredMode.REQUIRED
            ),
            minItems = 1,
            arraySchema = @Schema(
                    description = "Список данных о прокатах книг",
                    example = """
                {
                  "username": "ivanov_ii",
                  "userFullName": "Иванов Иван Иванович",
                  "userActive": true,
                  "bookName": "Преступление и наказание",
                  "bookAuthor": "Достоевский Ф.М."
                }"""
            )
    )
    @JsonProperty("data")
    @Valid
    @NotEmpty(message = "Список книг не может быть пустым")
    private List<BookRentalMigration> bookRentalMigrations;

}
