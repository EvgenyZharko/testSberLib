package ru.zharko.sberlib.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public ResourceNotFoundException( ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public ResourceNotFoundException( ErrorCode errorCode, String message, Throwable cause){
        super(message, cause);
        this.errorCode = errorCode;
    }

    public enum ErrorCode {
        BOOK_NOT_FOUND,
        SEASON_TICKET_NOT_FOUND,
        BOOK_RENTAL_NOT_FOUND
    }

}
