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
@Schema(description = "Данные о прокате книги")
public class BookRentalDto {

    @Schema(description = "ID проката книги", example = "222")
    private Long bookRentalId;

    @Schema(
            description = "Данные абонемента",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = SeasonTicketDto.class
    )
    private SeasonTicketDto seasonTicketDto;

    @Schema(
            description = "Данные книги",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = BookDto.class
    )
    private BookDto bookDto;

    @Schema(
            description = "Дата выдачи книги",
            example = "2025-04-15",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "date"
    )
    private LocalDate dateOfIssue;

    @Schema(
            description = "Дата возврата книги (null если книга еще не возвращена)",
            example = "2024-06-15",
            nullable = true,
            type = "string",
            format = "date"
    )
    private LocalDate returnDate;
}
