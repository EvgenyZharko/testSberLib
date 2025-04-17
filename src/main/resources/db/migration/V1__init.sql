-- Cоздание таблицы books - книги
CREATE SEQUENCE IF NOT EXISTS books_id_seq;
CREATE TABLE IF NOT EXISTS books (
    book_id INTEGER DEFAULT nextval('books_id_seq') PRIMARY KEY,
    title VARCHAR(300) NOT NULL,
    author VARCHAR(300) NOT NULL,
    date_of_publication DATE,
    CONSTRAINT uk_book_title_author UNIQUE (title, author)
    );

-- Cоздание таблицы season_tickets - абонементы
CREATE SEQUENCE IF NOT EXISTS season_tickets_id_seq;
CREATE TABLE IF NOT EXISTS season_tickets(
    season_ticket_id INTEGER DEFAULT nextval ('season_tickets_id_seq') PRIMARY KEY,
    client_username VARCHAR (100) NOT NULL UNIQUE,
    client_full_name VARCHAR (300) NOT NULL,
    season_ticket_status boolean DEFAULT true NOT NULL
     );

-- Cоздание таблицы book_rental - прокат книг
CREATE SEQUENCE IF NOT EXISTS book_rental_id_seq;
CREATE TABLE IF NOT EXISTS book_rental(
    book_rental_id INTEGER DEFAULT nextval ('book_rental_id_seq') PRIMARY KEY,
    season_ticket INTEGER REFERENCES season_tickets (season_ticket_id) NOT NULL,
    book_id INTEGER REFERENCES books (book_id) NOT NULL,
    date_of_issue DATE NOT NULL,
    date_of_return DATE
    );