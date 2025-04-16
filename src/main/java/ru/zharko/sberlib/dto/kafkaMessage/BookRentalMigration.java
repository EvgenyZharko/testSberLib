package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class BookRentalMigration {
    @JsonProperty("username")
    @NotBlank(message = "Поле userName не может быть пустым")
    private String userName;

    @JsonProperty("userFullName")
    @NotBlank(message = "Поле userFullName не может быть пустым")
    private String userFullName;

    @JsonProperty("userActive")
    @NotNull(message = "Поле userActive не может быть пустым")
    private boolean userActive;

    @JsonProperty("bookName")
    @NotBlank(message = "Поле bookName не может быть пустым")
    private String bookName;

    @JsonProperty("bookAuthor")
    @NotBlank(message = "Поле bookAuthor не может быть пустым")
    private String bookAuthor;
}
