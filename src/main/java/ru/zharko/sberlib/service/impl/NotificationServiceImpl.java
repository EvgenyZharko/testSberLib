package ru.zharko.sberlib.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.zharko.sberlib.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    public void sendMessage(String mailTo, String subject, String messageText) {
        log.info("\nПредставим, что вот тут мы отправляем письмо человеку на почту.\n" +
                "Адресат: {}\n" +
                "Тема письма: {}\n" +
                "Тело письма: {}\n\n", mailTo, subject, messageText);
    }
}
