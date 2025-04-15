package ru.zharko.sberlib.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import ru.zharko.sberlib.dto.kafkaMessage.BookRentalMigration;
import ru.zharko.sberlib.service.BookRentalService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaBookRentalMigrationConsumer {

    private final BookRentalService bookRentalService;

    /*Тут вынужден был фантазировать.
    * В задании не указано, как именно обрабатывать кейс с отсутствием книги или абонемента в БД.
    * Учитывая, что лучше получить данные и разобраться позже, чем вообще потерять, решил сделать так.
    *
    * Книга: ищу по комбинации Название + Автор (сделал эту комбинацию на таблице уникальной).
    * В случае, если найдена, использую найденную книгу.
    * В случае, если не найдена, создаю новую (пишу в БД)
    *
    * Абонемент: ищу по userName, Так как на нем висит уникальность на уровне БД. Вешать уникальность на ФИО - не оч логично
    * В случае, если абонемент найден, использую его.
    * В случае, если нет, создаю новый абонемент (пишу в БД)
    *
    * Создаю новую запись об аренде книги, предполагаю, что если это архивные данные, то книга возвращена
    * Потому заполняю дату выдачи (она неизвестна) и дату возврата (она неизвестна) сегодняшним числом.
    *
    * В иной ситуации пошел бы на беседу к анализу. Но в данном случае такой возможности не имею
    * */
    @KafkaListener(topics = {"${spring.kafka.topic.book-rental-migration}"},
            groupId = "${spring.kafka.consumer.group-id}")
    public void listen(List<BookRentalMigration> rentals, Acknowledgment ack) {
        bookRentalService.prepareBookRentalListFromKafka(rentals);
        ack.acknowledge();
    }

}
