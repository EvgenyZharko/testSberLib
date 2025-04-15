package ru.zharko.sberlib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonTicketDto {
    private Long seasonTicketId;
    private String clientUsername;
    private String clientFullName;
    private Boolean seasonTicketStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;
}
