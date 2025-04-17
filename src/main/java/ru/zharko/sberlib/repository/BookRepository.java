package ru.zharko.sberlib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zharko.sberlib.model.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByBookId(Long bookId);

    boolean existsByTitleAndAuthorIgnoreCase(String bookTitle, String author);

    Optional<Book> findByTitleAndAuthorIgnoreCase(String bookTitle, String author);

    @Query(value = "SELECT b.* FROM books b\n" +
                    "JOIN book_rental br ON b.book_id = br.book_id\n" +
                    "WHERE br.season_ticket = :seasonTicketId\n" +
                    "AND br.date_of_return IS NULL",
            nativeQuery = true)
    List<Book> findBookInRentBySeasonTicketId(Long seasonTicketId);
}
