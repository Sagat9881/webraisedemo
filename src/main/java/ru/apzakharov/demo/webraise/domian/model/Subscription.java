package ru.apzakharov.demo.webraise.domian.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Subscription {
    private String subscriptionName;
    private Set<User> users;

}
