package ru.apzakharov.demo.webraise.application.exception;

public class AddSubscriptionException extends RuntimeException {
    public AddSubscriptionException(Exception e) {
        super(e);
    }
}
