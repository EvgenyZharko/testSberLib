package ru.zharko.sberlib.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.zharko.sberlib.model.BookRental;
import java.util.List;

@Repository
public interface BookRentalRepository extends JpaRepository<BookRental, Long> {

    boolean existsByBookRentalId(Long bookRentalId);

    @EntityGraph(attributePaths = {"seasonTicket", "book"})
    BookRental save(BookRental bookRental);

    @Query(value = "SELECT * FROM book_rental WHERE date_of_return IS NULL\n" +
            "AND (CURRENT_DATE - book_rental.date_of_issue) > 20\n",
    nativeQuery = true)
    List<BookRental> findAllExceedingRentBook();
}
