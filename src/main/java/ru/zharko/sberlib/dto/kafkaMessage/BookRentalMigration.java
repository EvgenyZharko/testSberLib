package ru.zharko.sberlib.dto.kafkaMessage;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String userName;

    @JsonProperty("userFullName")
    private String userFullName;

    @JsonProperty("userActive")
    private boolean userActive;

    @JsonProperty("bookName")
    private String bookName;

    @JsonProperty("bookAuthor")
    private String bookAuthor;
}
