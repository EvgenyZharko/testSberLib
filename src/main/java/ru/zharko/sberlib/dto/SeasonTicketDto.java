package ru.zharko.sberlib.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO абонемента читателя")
public class SeasonTicketDto {

    @Schema(description = "ID абонемента", example = "1")
    private Long seasonTicketId;

    @Schema(
            description = "Username клиента",
            example = "ivanov123",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String clientUsername;

    @Schema(
            description = "ФИО клиента",
            example = "Иванов Иван Иванович",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String clientFullName;

    @Schema(description = "Статус абонемента", example = "true")
    private Boolean seasonTicketStatus;

    @Schema(
            description = "Список взятых книг (только при withBooks=true)",
            nullable = true,
            implementation = BookDto.class
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<BookDto> books;
}
