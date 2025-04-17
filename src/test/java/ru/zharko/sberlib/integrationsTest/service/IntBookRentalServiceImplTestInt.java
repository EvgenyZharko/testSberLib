package ru.zharko.sberlib.integrationsTest.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.kafka.KafkaBookRentalMigrationProducer;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRentalRepository;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.BookRentalService;
import ru.zharko.sberlib.utils.TestObjects;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class IntBookRentalServiceImplTestInt {

    @Autowired
    private KafkaBookRentalMigrationProducer producer;
    @Autowired
    private BookRentalRepository bookRentalRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private SeasonTicketRepository seasonTicketRepository;
    @Autowired
    private BookRentalService bookRentalService;

    @Test
    void prepareBookRentalListFromKafka_shouldSaveBookRentalList() {
        List<BookRentalMigration> rentals = TestObjects.getBookRentalMigrationList();

        Long countInBookRepository = bookRepository.count();
        Long countSeasonTicketRepository = seasonTicketRepository.count();
        Long countBookRentalRepository = bookRentalRepository.count();

        bookRentalService.prepareBookRentalListFromKafka(rentals);

        assertThat(bookRepository.count()).isGreaterThan(countInBookRepository);
        assertThat(seasonTicketRepository.count()).isGreaterThan(countSeasonTicketRepository);
        assertThat(bookRentalRepository.count()).isGreaterThan(countBookRentalRepository);

        Book book1 = bookRepository.findByTitleAndAuthorIgnoreCase("Тест книга 1", "Тест автор 1").orElseThrow();
        Book book2 = bookRepository.findByTitleAndAuthorIgnoreCase("Тест книга 2", "Тест автор 2").orElseThrow();

        assertThat(book1).isNotNull();
        assertThat(book2).isNotNull();

        SeasonTicket seasonTicket1 = seasonTicketRepository.findByClientUsernameIgnoreCase("test1").orElseThrow();
        SeasonTicket seasonTicket2 = seasonTicketRepository.findByClientUsernameIgnoreCase("test2").orElseThrow();

        assertThat(seasonTicket1).isNotNull();
        assertThat(seasonTicket2).isNotNull();
    }

}
