package ru.apzakharov.demo.webraise.application.exception.subscription;

public class GetSubscriberListException extends RuntimeException {
    public GetSubscriberListException(Exception e) {
        super(e);
    }
}
