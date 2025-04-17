package ru.zharko.sberlib.unitTest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.zharko.sberlib.controller.impl.BookRentalControllerImpl;
import ru.zharko.sberlib.dto.kafkaMessage.KafkaMessageBookRentalMigration;
import ru.zharko.sberlib.service.BookRentalService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookRentalControllerImplTest {
    @Mock
    private BookRentalService bookRentalService;

    @InjectMocks
    private BookRentalControllerImpl controller;

    @Test
    void loadBookRentals_WithBooksTrue() {
        KafkaMessageBookRentalMigration messageToKafka = new KafkaMessageBookRentalMigration();

        assertDoesNotThrow(() ->  controller.loadBookRentals(messageToKafka));
        verify(bookRentalService, times(1)).loadBookRentals(messageToKafka);
    }
}
