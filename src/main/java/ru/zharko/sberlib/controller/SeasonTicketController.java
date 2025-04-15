package ru.zharko.sberlib.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import ru.zharko.sberlib.dto.SeasonTicketDto;

@RequestMapping("/season-tickets")
public interface SeasonTicketController {
    SeasonTicketDto findByClientName(String clientName, boolean withBooks);
}
