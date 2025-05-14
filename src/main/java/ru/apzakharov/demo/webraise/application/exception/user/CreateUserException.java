package ru.apzakharov.demo.webraise.application.exception.user;

public class CreateUserException extends RuntimeException{

    public CreateUserException(String message) {
        super(message);
    }
}
