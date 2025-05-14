package ru.apzakharov.demo.webraise.application.exception.user;

public class DeleteUserException extends RuntimeException {
    public DeleteUserException(Exception e) {
        super(e);
    }
}
