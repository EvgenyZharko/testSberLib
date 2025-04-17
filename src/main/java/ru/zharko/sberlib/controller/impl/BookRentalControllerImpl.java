package ru.zharko.sberlib.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.zharko.sberlib.controller.BookRentalController;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.service.BookRentalService;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "API Прокат книг", description = "API для работы с прокатом книг")
public class BookRentalControllerImpl implements BookRentalController {

    private final BookRentalService bookRentalService;

    @Operation(
            summary = "Загрузить архивные данные о прокате книг",
            description = """
                    Принимает пакет данных о прокате книг в виде JSON файла.
                    Производит отправку сообщения в Kafka""",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Данные добавлены"),
                    @ApiResponse(responseCode = "400", description = "Ошибка в отправленном файле"),
                    @ApiResponse(responseCode = "500", description = "При обработке запроса произошла ошибка")
            }
    )
    @PostMapping("/load-book-rentals")
    public void loadBookRentals(@RequestBody KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations) {
        bookRentalService.loadBookRentals(kafkaMessageBookRentalMigrations);
    }
}
