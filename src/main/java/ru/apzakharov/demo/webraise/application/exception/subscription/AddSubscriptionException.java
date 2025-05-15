package ru.apzakharov.demo.webraise.application.exception.subscription;

public class AddSubscriptionException extends RuntimeException {
    public AddSubscriptionException(Exception e) {
        super(e);
    }
}
