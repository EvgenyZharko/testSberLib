package ru.zharko.sberlib.integrationsTest.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zharko.sberlib.dto.BookDto;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.BookRental;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRentalRepository;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.impl.SeasonTicketServiceImpl;
import ru.zharko.sberlib.utils.TestObjects;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class IntSeasonTicketServiceImplTestInt {

    @Autowired
    private SeasonTicketServiceImpl seasonTicketService;

    @Autowired
    private SeasonTicketRepository seasonTicketRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRentalRepository bookRentalRepository;

    @BeforeEach
    void setUp() {
        // создаём абонемент
        SeasonTicket seasonTicket = TestObjects.getSeasonTicketWithoutId();
        seasonTicketRepository.save(seasonTicket);

        // создаём книги
        Book book1 = TestObjects.getBookWithoutIdOne();
        bookRepository.save(book1);
        Book book2 = TestObjects.getBookWithoutIdTwo();
        bookRepository.save(book2);

        LocalDate today = LocalDate.now();

        BookRental bookRental1 = new BookRental();
        bookRental1.setSeasonTicket(seasonTicket);
        bookRental1.setBook(book1);
        bookRental1.setDateOfIssue(today);
        bookRentalRepository.save(bookRental1);

        BookRental bookRental2 = new BookRental();
        bookRental2.setSeasonTicket(seasonTicket);
        bookRental2.setBook(book2);
        bookRental2.setDateOfIssue(today);
        bookRentalRepository.save(bookRental2);
    }

    @Test
    void findByClientName_withBooks_shouldReturnDtoWithBooks() {
        SeasonTicketDto seasonTicketDto = seasonTicketService.findByClientName("Тестовый", true);

        assertThat(seasonTicketDto).isNotNull();
        assertThat(seasonTicketDto.getClientFullName()).isEqualTo("Тестовый");
        assertThat(seasonTicketDto.getBooks()).hasSize(2);
        assertThat(seasonTicketDto.getBooks())
                .extracting(BookDto::getTitle)
                .containsExactlyInAnyOrder("Книга 1", "Книга 2");
    }

    @Test
    void findByClientName_withoutBooks_shouldReturnDtoWithoutBooks() {
        SeasonTicketDto seasonTicketDto = seasonTicketService.findByClientName("Тестовый", false);

        assertThat(seasonTicketDto).isNotNull();
        assertThat(seasonTicketDto.getClientFullName()).isEqualTo("Тестовый");
        assertThat(seasonTicketDto.getBooks()).isNull();
    }

}
