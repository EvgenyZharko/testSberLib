package ru.zharko.sberlib.utils;

import ru.zharko.sberlib.dto.BookDto;
import ru.zharko.sberlib.dto.SeasonTicketDto;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.model.Book;
import ru.zharko.sberlib.model.SeasonTicket;
import java.util.ArrayList;
import java.util.List;

public class TestObjects {

    public static Book getBookWithoutIdOne() {
        Book book = new Book();
        book.setTitle("Книга 1");
        book.setAuthor("Автор 1");
        return book;
    }

    public static Book getBookWithoutIdTwo() {
        Book book = new Book();
        book.setTitle("Книга 2");
        book.setAuthor("Автор 2");
        return book;
    }

    public static SeasonTicket getSeasonTicketWithoutId() {
        SeasonTicket seasonTicket = new SeasonTicket();
        seasonTicket.setClientUsername("testoff");
        seasonTicket.setClientFullName("Тестовый");
        seasonTicket.setSeasonTicketStatus(true);
        return seasonTicket;
    }

    public static SeasonTicket getSeasonTicketOne() {
        SeasonTicket seasonTicket = new SeasonTicket();
        seasonTicket.setSeasonTicketId(1L);
        seasonTicket.setClientUsername("ivanov111");
        seasonTicket.setClientFullName("Иванов");
        seasonTicket.setSeasonTicketStatus(true);
        return seasonTicket;
    }

    public static Book getBookOne() {
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Книга 1");
        book.setAuthor("Автор 1");
        return book;
    }

    public static Book getBookTwo() {
        Book book = new Book();
        book.setBookId(2L);
        book.setTitle("Книга 2");
        book.setAuthor("Автор 2");
        return book;
    }

    public static List<Book> getBookList() {
        List<Book> books = new ArrayList<>();
        books.add(getBookOne());
        books.add(getBookTwo());
        return books;
    }

    public static SeasonTicketDto getSeasonTicketDtoOne() {
        SeasonTicketDto seasonTicketDto = new SeasonTicketDto();
        seasonTicketDto.setSeasonTicketId(1L);
        seasonTicketDto.setClientUsername("ivanov111");
        seasonTicketDto.setClientFullName("Иванов");
        seasonTicketDto.setSeasonTicketStatus(true);
        return seasonTicketDto;
    }

    public static SeasonTicketDto getSeasonTicketDtoWithBooks() {
        SeasonTicketDto seasonTicketDto= getSeasonTicketDtoOne();
        seasonTicketDto.setBooks(getBookDtoList());
        return seasonTicketDto;
    }

    public static BookDto getBookDtoOne() {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(1L);
        bookDto.setTitle("Книга 1");
        bookDto.setAuthor("Автор 1");
        return bookDto;
    }

    public static BookDto getBookDtoTwo() {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(2L);
        bookDto.setTitle("Книга 2");
        bookDto.setAuthor("Автор 2");
        return bookDto;
    }

    public static List<BookDto> getBookDtoList() {
        List<BookDto> books = new ArrayList<>();
        books.add(getBookDtoOne());
        books.add(getBookDtoTwo());
        return books;
    }

    public static BookRentalMigration getBookRentalMigrationOne() {
        BookRentalMigration bookRentalMigration = new BookRentalMigration();
        bookRentalMigration.setUserName("test1");
        bookRentalMigration.setUserFullName("Тест Пользователь 1");
        bookRentalMigration.setUserActive(true);
        bookRentalMigration.setBookName("Тест книга 1");
        bookRentalMigration.setBookAuthor("Тест автор 1");
        return bookRentalMigration;
    }

    public static BookRentalMigration getBookRentalMigrationTwo() {
        BookRentalMigration bookRentalMigration = new BookRentalMigration();
        bookRentalMigration.setUserName("test2");
        bookRentalMigration.setUserFullName("Тест Пользователь 2");
        bookRentalMigration.setUserActive(true);
        bookRentalMigration.setBookName("Тест книга 2");
        bookRentalMigration.setBookAuthor("Тест автор 2");
        return bookRentalMigration;
    }

    public static List<BookRentalMigration> getBookRentalMigrationList() {
        List<BookRentalMigration> bookRentalMigrationList = new ArrayList<>();
        bookRentalMigrationList.add(getBookRentalMigrationOne());
        bookRentalMigrationList.add(getBookRentalMigrationTwo());
        return bookRentalMigrationList;
    }

    public static KafkaMessageBookRentalMigration getKafkaMessageBookRentalMigration() {
        KafkaMessageBookRentalMigration message = new KafkaMessageBookRentalMigration();
        message.setBookRentalMigrations(getBookRentalMigrationList());
        return message;
    }
}
