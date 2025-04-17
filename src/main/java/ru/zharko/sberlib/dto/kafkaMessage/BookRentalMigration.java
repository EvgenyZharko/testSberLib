package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "DTO для миграции данных о прокате книг из Kafka")
@ToString
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BookRentalMigration {
    @Schema(
            description = "Уникальный логин пользователя в системе",
            example = "user123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("username")
    @NotBlank(message = "Поле userName не может быть пустым")
    private String userName;

    @Schema(
            description = "Полное имя пользователя",
            example = "Иванов Иван Иванович",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("userFullName")
    @NotBlank(message = "Поле userFullName не может быть пустым")
    private String userFullName;

    @Schema(
            description = "Статус активности абонемента",
            example = "true",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("userActive")
    @NotNull(message = "Поле userActive не может быть пустым")
    private boolean userActive;

    @Schema(
            description = "Название книги",
            example = "Сомнабулический поиск неведомого кадата",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("bookName")
    @NotBlank(message = "Поле bookName не может быть пустым")
    private String bookName;

    @Schema(
            description = "Автор книги",
            example = "Говард Филлипс Лавкрафт",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("bookAuthor")
    @NotBlank(message = "Поле bookAuthor не может быть пустым")
    private String bookAuthor;
}
