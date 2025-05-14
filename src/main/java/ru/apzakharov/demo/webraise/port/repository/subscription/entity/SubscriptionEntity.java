package ru.apzakharov.demo.webraise.port.repository.subscription.entity;

import jakarta.persistence.*;
import lombok.Builder;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;
import ru.apzakharov.demo.webraise.port.repository.user.entity.UserEntity;

import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Table
public class SubscriptionEntity implements EntityWithId<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String subscriptionName;

    // Many-to-Many relationship with User
    @ManyToMany(mappedBy = "subscriptions")
    private Set<UserEntity> users;


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return EntityWithId.super.isNew();
    }
}
