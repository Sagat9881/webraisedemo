package ru.apzakharov.demo.webraise.domian.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Subscription {
    private String subscriptionName;
    private UUID id;
    private Set<User> users;

}
