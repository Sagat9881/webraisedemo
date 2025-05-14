package ru.apzakharov.demo.webraise.application.exception.user;

public class UserUpdateException extends RuntimeException {
    public UserUpdateException(Exception e) {
        super(e);
    }
}
