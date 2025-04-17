package ru.zharko.sberlib.unitTest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.kafka.KafkaBookRentalMigrationProducer;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.BookRental;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRentalRepository;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.impl.BookRentalServiceImpl;
import ru.zharko.sberlib.utils.TestObjects;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookRentalServiceImplTest {
    @Mock
    private KafkaBookRentalMigrationProducer producer;
    @Mock
    private BookRentalRepository bookRentalRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private SeasonTicketRepository seasonTicketRepository;
    @InjectMocks
    private BookRentalServiceImpl bookRentalService;

    @Test
    void loadBookRentals_shouldSendEachRentalToKafka(){
        KafkaMessageBookRentalMigration message = TestObjects.getKafkaMessageBookRentalMigration();

        bookRentalService.loadBookRentals(message);

        verify(producer, times(1)).sendBookRentalToTopic(message.getBookRentalMigrations().get(0));
        verify(producer, times(1)).sendBookRentalToTopic(message.getBookRentalMigrations().get(1));
    }

    @Test
    void prepareBookRentalListFromKafka_shouldCreateEntitiesIfNotExist() {
        BookRentalMigration rental = TestObjects.getBookRentalMigrationOne();

        when(bookRepository.findByTitleAndAuthorIgnoreCase("Тест книга 1", "Тест автор 1")).thenReturn(Optional.empty());
        when(seasonTicketRepository.findByClientUsernameIgnoreCase("test1")).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(seasonTicketRepository.save(any(SeasonTicket.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(bookRentalRepository.save(any(BookRental.class))).thenAnswer(invocation -> invocation.getArgument(0));

        bookRentalService.prepareBookRentalListFromKafka(List.of(rental));

        verify(bookRepository, times(1)).save(any(Book.class));
        verify(seasonTicketRepository, times(1)).save(any(SeasonTicket.class));
        verify(bookRentalRepository, times(1)).save(any(BookRental.class));
    }

    @Test
    void prepareBookRentalListFromKafka_shouldUseCacheAndAvoidDuplicateDbCalls() {
        BookRentalMigration rental1 = TestObjects.getBookRentalMigrationOne();
        BookRentalMigration rental2 = TestObjects.getBookRentalMigrationOne();

        when(bookRepository.findByTitleAndAuthorIgnoreCase("Тест книга 1", "Тест автор 1")).thenReturn(Optional.empty());
        when(seasonTicketRepository.findByClientUsernameIgnoreCase("test1")).thenReturn(Optional.empty());
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(seasonTicketRepository.save(any(SeasonTicket.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(bookRentalRepository.save(any(BookRental.class))).thenAnswer(invocation -> invocation.getArgument(0));

        bookRentalService.prepareBookRentalListFromKafka(List.of(rental1, rental2));

        verify(bookRepository, times(1)).save(any(Book.class));
        verify(seasonTicketRepository, times(1)).save(any(SeasonTicket.class));
        verify(bookRentalRepository, times(2)).save(any(BookRental.class));
        verify(bookRepository, times(1)).findByTitleAndAuthorIgnoreCase("Тест книга 1", "Тест автор 1");
        verify(seasonTicketRepository, times(1)).findByClientUsernameIgnoreCase("test1");
    }
}
