package ru.apzakharov.demo.webraise.port.repository;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public interface EntityWithId <T extends Serializable> extends Persistable<T> {
    T getId();

    @Override
    default boolean isNew() {
        return getId() == null;
    }
}