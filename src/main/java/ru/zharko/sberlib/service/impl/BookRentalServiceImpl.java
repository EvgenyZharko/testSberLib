package ru.zharko.sberlib.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.dto.mapKeys.BookTitleAuthorMapKey;
import ru.zharko.sberlib.kafka.KafkaBookRentalMigrationProducer;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.BookRental;
import ru.zharko.sberlib.model.SeasonTicket;
import ru.zharko.sberlib.repository.BookRentalRepository;
import ru.zharko.sberlib.repository.BookRepository;
import ru.zharko.sberlib.repository.SeasonTicketRepository;
import ru.zharko.sberlib.service.BookRentalService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRentalServiceImpl implements BookRentalService {

    private final KafkaBookRentalMigrationProducer producer;
    private final BookRentalRepository bookRentalRepository;
    private final BookRepository bookRepository;
    private final SeasonTicketRepository seasonTicketRepository;

    public void prepareBookRentalListFromKafka(List<BookRentalMigration> rentals) {

        Map<String, SeasonTicket> cacheSeasonTickets = new HashMap<>();
        Map<BookTitleAuthorMapKey, Book> cacheBooks = new HashMap<>();

        for (BookRentalMigration rental : rentals) {

            BookRental bookRental = new BookRental();

            BookTitleAuthorMapKey booksKey = new BookTitleAuthorMapKey(rental.getBookName(), rental.getBookAuthor());
            Book book;
            SeasonTicket seasonTicket;

            if (cacheBooks.containsKey(booksKey)) {
                book = cacheBooks.get(booksKey);
            } else {
                Optional<Book> bookFromDb = bookRepository.findByTitleAndAuthorIgnoreCase(rental.getBookName(), rental.getBookAuthor());
                if (bookFromDb.isPresent()) {
                    book = bookFromDb.get();
                    cacheBooks.put(booksKey, book);
                } else {
                    book = new Book();
                    book.setTitle(rental.getBookName());
                    book.setAuthor(rental.getBookAuthor());
                    bookRepository.save(book);
                    cacheBooks.put(booksKey, book);
                }
            }

            if (cacheSeasonTickets.containsKey(rental.getUserName())) {
                seasonTicket = cacheSeasonTickets.get(rental.getUserName());
            } else {
                Optional<SeasonTicket> seasonTicketFromDb = seasonTicketRepository.findByClientUsernameIgnoreCase(rental.getUserName());
                if (seasonTicketFromDb.isPresent()) {
                    seasonTicket = seasonTicketFromDb.get();
                    cacheSeasonTickets.put(seasonTicket.getClientUsername(), seasonTicket);
                } else {
                    seasonTicket = new SeasonTicket();
                    seasonTicket.setClientFullName(rental.getUserFullName());
                    seasonTicket.setClientUsername(rental.getUserName());
                    seasonTicket.setSeasonTicketStatus(rental.isUserActive());
                    seasonTicketRepository.save(seasonTicket);
                    cacheSeasonTickets.put(seasonTicket.getClientUsername(), seasonTicket);
                }
            }

            bookRental.setBook(book);
            bookRental.setSeasonTicket(seasonTicket);
            bookRental.setDateOfIssue(LocalDate.now());
            bookRental.setReturnDate(LocalDate.now());
            bookRentalRepository.save(bookRental);
        }

        cacheBooks.clear();
        cacheSeasonTickets.clear();
    }

    public void loadBookRentals(KafkaMessageBookRentalMigration kafkaMessageBookRentalMigrations) {
        List<BookRentalMigration> rentals = kafkaMessageBookRentalMigrations.getBookRentalMigrations();
        for (BookRentalMigration rental : rentals) {
            producer.sendBookRentalToTopic(rental);
        }
    }
}
