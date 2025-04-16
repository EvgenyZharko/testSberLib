package ru.zharko.sberlib.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharko.sberlib.dto.SeasonTicketDto;

@RequestMapping("/season-tickets")
public interface SeasonTicketController {
    SeasonTicketDto findByClientName(@NotBlank(message = "Имя клиента не может быть пустым") String clientName, boolean withBooks);
}
