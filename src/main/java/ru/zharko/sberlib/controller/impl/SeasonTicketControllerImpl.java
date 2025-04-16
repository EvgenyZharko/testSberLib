package ru.zharko.sberlib.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zharko.sberlib.controller.SeasonTicketController;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.service.SeasonTicketService;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API Абонементы", description = "API для работы с абонементами читателей")
public class SeasonTicketControllerImpl implements SeasonTicketController {

    private final SeasonTicketService seasonTicketService;

    @Operation(
            summary = "Поиск абонементов по ФИО читателя",
            description = """
                    Возвращает данные об абонементе. Возможно опционально получить информацию о взятых книгах.
                    При проставленном значении withBooks = true к ответу добавляется список книг,
                    которые пользователь взял в прокат, но еще не вернул (не указана дата возврата).
                    """,
            parameters = {
                    @Parameter(
                            name = "clientName",
                            description = "ФИО читателя для поиска",
                            required = true,
                            example = "Крузенштерн Иван Федорович",
                            in = ParameterIn.QUERY
                    ),
                    @Parameter(
                            name = "withBooks",
                            description = "Флаг - требуется ли список взятых книг",
                            example = "true",
                            in = ParameterIn.QUERY
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Абонемент найден"),
                    @ApiResponse(responseCode = "404", description = "Абонемент не найден"),
                    @ApiResponse(responseCode = "500", description = "При обработке запроса произошла ошибка")
            }
    )
    @GetMapping("/by-client-name")
    public SeasonTicketDto findByClientName(@RequestParam String clientName,
                                            @RequestParam(required = false, defaultValue = "false") boolean withBooks) {
        return seasonTicketService.findByClientName(clientName, withBooks);
    }
}
