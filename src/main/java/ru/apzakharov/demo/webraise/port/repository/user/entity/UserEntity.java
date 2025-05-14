package ru.apzakharov.demo.webraise.port.repository.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;
import ru.apzakharov.demo.webraise.port.repository.subscription.entity.SubscriptionEntity;

import java.util.Set;
import java.util.UUID;

@Entity
@Builder
public class UserEntity implements EntityWithId<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String email;

    // Many-to-Many relationship with Subscription
    @ManyToMany
    @JoinTable(
            name = "user_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id"))
    private Set<SubscriptionEntity> subscriptions;


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return EntityWithId.super.isNew();
    }
}
