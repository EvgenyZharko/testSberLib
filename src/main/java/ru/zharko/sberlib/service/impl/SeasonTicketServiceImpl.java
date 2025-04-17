package ru.zharko.sberlib.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.exception.ResourceNotFoundException;
import ru.zharko.sberlib.mapper.BookMapper;
import ru.zharko.sberlib.mapper.SeasonTicketMapper;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.SeasonTicketService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonTicketServiceImpl implements SeasonTicketService {

    private final SeasonTicketRepository seasonTicketRepository;
    private final BookRepository bookRepository;
    private final SeasonTicketMapper seasonTicketMapper;
    private final BookMapper bookMapper;

    public SeasonTicketDto findByClientName(String clientName, boolean withBooks) {
        SeasonTicket seasonTicket = seasonTicketRepository.findByClientFullNameIgnoreCase(clientName).orElseThrow(() ->
                new ResourceNotFoundException(ResourceNotFoundException.ErrorCode.SEASON_TICKET_NOT_FOUND,
                        "Абонементы для пользователя с именем \"" + clientName + "\" не найдены"));
        SeasonTicketDto seasonTicketDto = seasonTicketMapper.toDto(seasonTicket);

        if (withBooks) {
            List<Book> books = bookRepository.findBookInRentBySeasonTicketId(seasonTicket.getSeasonTicketId());
            if (books != null) {
                seasonTicketDto.setBooks(bookMapper.toListDto(books));
            }
        }
        return seasonTicketDto;
    }
}
