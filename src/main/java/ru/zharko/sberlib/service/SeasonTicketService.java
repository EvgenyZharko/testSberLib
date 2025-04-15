package ru.zharko.sberlib.service;

import ru.zharko.sberlib.dto.SeasonTicketDto;

public interface SeasonTicketService {
    SeasonTicketDto findByClientName(String clientName, boolean withBooks);
}
