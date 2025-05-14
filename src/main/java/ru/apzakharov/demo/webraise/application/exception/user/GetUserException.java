package ru.apzakharov.demo.webraise.application.exception.user;

public class GetUserException extends RuntimeException {
    public GetUserException(Exception e) {
        super(e);
    }
}
