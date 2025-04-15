package ru.zharko.sberlib.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zharko.sberlib.controller.SeasonTicketController;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.service.SeasonTicketService;

@RestController
@RequiredArgsConstructor
public class SeasonTicketControllerImpl implements SeasonTicketController {

    private final SeasonTicketService seasonTicketService;

    //Поиск абонемента по ФИО. В качестве параметра withBook - true/false.
    // Ищет книги, взятые в прокат, где не проставлена дата возврата
    @GetMapping("/by-client-name")
    public SeasonTicketDto findByClientName(@RequestParam String clientName,
                                            @RequestParam(required = false, defaultValue = "false") boolean withBooks) {
        return seasonTicketService.findByClientName(clientName, withBooks);
    }
}
