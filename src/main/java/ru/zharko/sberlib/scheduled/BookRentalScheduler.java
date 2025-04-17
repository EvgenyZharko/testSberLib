package ru.zharko.sberlib.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.zharko.sberlib.model.BookRental;
import ru.zharko.sberlib.repository.BookRentalRepository;
import ru.zharko.sberlib.service.NotificationService;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRentalScheduler {

    private final NotificationService notificationService;
    private final BookRentalRepository bookRentalRepository;

    @Scheduled(cron = "${scheduler.notify-about-exceeding-rent.cron}")
    public void notifyAboutExceedingRent() {
        List<BookRental> expiredBooks = bookRentalRepository.findAllExceedingRentBook();
        if (expiredBooks != null && !expiredBooks.isEmpty()) {
            for (BookRental rent : expiredBooks) {
                String mailTo = "1@gmail.com"; //но так бы взяли из seasonTicket
                String subject = createSubjectText(rent);
                String messageText = createMessageText(rent);
                notificationService.sendMessage(mailTo, subject, messageText);
            }
        }
    }

    private String createSubjectText(BookRental rent) {
        StringBuilder subject = new StringBuilder();
        subject.append("Уведомление о просрочке возврата книги \"");
        subject.append(rent.getBook().getTitle());
        subject.append("\" Автора ");
        subject.append(rent.getBook().getAuthor());
        return subject.toString();
    }

    private String createMessageText(BookRental rent) {
        StringBuilder messageText = new StringBuilder();
        messageText.append("Уважаемый ");
        messageText.append(rent.getSeasonTicket().getClientFullName());
        messageText.append("\nТакой ты, конечно, негодяй.\nВзял ");
        messageText.append(rent.getDateOfIssue());
        messageText.append(" книжку: Автор ");
        messageText.append(rent.getBook().getAuthor());
        messageText.append(" \"");
        messageText.append(rent.getBook().getTitle());
        messageText.append("\"\n");
        messageText.append("И до сих пор не вернул. Жди, за тобой выехали.");
        return messageText.toString();
    }
}
