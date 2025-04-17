package ru.zharko.sberlib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO книги")
public class BookDto {
    @Schema(description = "ID книги", example = "1123")
    private Long bookId;

    @Schema(
            description = "Название книги",
            example = "Сомнабулический поиск неведомого кадата",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Автор книги",
            example = "Говард Филлипс Лавкрафт",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String author;

    @Schema(description = "Дата публикации книги", example = "1977-01-28")
    private LocalDate dateOfPublication;
}
