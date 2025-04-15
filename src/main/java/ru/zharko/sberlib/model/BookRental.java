package ru.zharko.sberlib.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "book_rental")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_rental_id_seq")
    @SequenceGenerator(name = "book_rental_id_seq", sequenceName = "book_rental_id_seq", allocationSize = 1)
    @Column(name = "book_rental_id")
    private Long bookRentalId;

    @ManyToOne
    @JoinColumn(name = "season_ticket", nullable = false)
    private SeasonTicket seasonTicket;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "date_of_issue", nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "date_of_return")
    private LocalDate returnDate;
}
