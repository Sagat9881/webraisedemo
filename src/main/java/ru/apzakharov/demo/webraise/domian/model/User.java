package ru.apzakharov.demo.webraise.domian.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class User {
    private String name;
    private String email;
    private UUID id;
    private Set<Subscription> subscriptions;
}
