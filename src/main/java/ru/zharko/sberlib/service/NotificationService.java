package ru.zharko.sberlib.service;

public interface NotificationService {
    void sendMessage(String mailTo, String subject, String messageText);
}
