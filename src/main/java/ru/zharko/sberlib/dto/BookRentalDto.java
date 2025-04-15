package ru.zharko.sberlib.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRentalDto {
    private Long bookRentalId;
    private SeasonTicketDto seasonTicketDto;
    private BookDto bookDto;
    private LocalDate dateOfIssue;
    private LocalDate returnDate;
}
