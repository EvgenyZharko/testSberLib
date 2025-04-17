package ru.zharko.sberlib.unitTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.zharko.sberlib.dto.BookDto;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.exception.ResourceNotFoundException;
import ru.zharko.sberlib.mapper.BookMapper;
import ru.zharko.sberlib.mapper.SeasonTicketMapper;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.impl.SeasonTicketServiceImpl;
import ru.zharko.sberlib.utils.TestObjects;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeasonTicketServiceImplTest {

    @Mock
    private SeasonTicketRepository seasonTicketRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private SeasonTicketMapper seasonTicketMapper;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private SeasonTicketServiceImpl seasonTicketService;

    private SeasonTicket seasonTicket;
    private SeasonTicketDto seasonTicketDto;

    @BeforeEach
    void setUp(){
        seasonTicket = TestObjects.getSeasonTicketOne();
        seasonTicketDto = TestObjects.getSeasonTicketDtoOne();
    }

    @Test
    void findByClientName_withBooks_shouldReturnSeasonTicketDtoWithBooks() {
        List<Book> books = TestObjects.getBookList();
        List<BookDto> booksDto = TestObjects.getBookDtoList();

        when(seasonTicketRepository.findByClientFullNameIgnoreCase("Иванов")).thenReturn(Optional.of(seasonTicket));
        when(seasonTicketMapper.toDto(seasonTicket)).thenReturn(seasonTicketDto);
        when(bookRepository.findBookInRentBySeasonTicketId(seasonTicket.getSeasonTicketId())).thenReturn(books);
        when(bookMapper.toListDto(books)).thenReturn(booksDto);

        SeasonTicketDto result = seasonTicketService.findByClientName("Иванов", true);

        assertNotNull(result);
        assertEquals(seasonTicketDto.getClientFullName(), result.getClientFullName());
        assertNotNull(result.getBooks());
        assertEquals(2, result.getBooks().size());
        assertEquals("Книга 1", result.getBooks().get(0).getTitle());
    }

    @Test
    void findByClientName_withoutBooks_shouldReturnSeasonTicketDtoWithoutBooks() {
        when(seasonTicketRepository.findByClientFullNameIgnoreCase("Иванов")).thenReturn(Optional.of(seasonTicket));
        when(seasonTicketMapper.toDto(seasonTicket)).thenReturn(seasonTicketDto);

        SeasonTicketDto result = seasonTicketService.findByClientName("Иванов", false);

        assertNotNull(result);
        assertEquals(seasonTicketDto.getClientFullName(), result.getClientFullName());
        assertNull(result.getBooks());
    }

    @Test
    void findByClientName_whenNotFound_shouldThrowResourceNotFoundException() {
        when(seasonTicketRepository.findByClientFullNameIgnoreCase("Несуществующий Клиент")).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            seasonTicketService.findByClientName("Несуществующий Клиент", false);
        });

        assertEquals("Абонементы для пользователя с именем \"Несуществующий Клиент\" не найдены", exception.getMessage());
    }
}
