package ru.apzakharov.demo.webraise.domian.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class User {
    private String name;
    private String email;
    private Set<Subscription> subscriptions;
}
