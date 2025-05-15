package ru.apzakharov.demo.webraise.port.repository.subscription;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SUBSCRIPTIONS", schema = "WEBRISE_DEMO")
public class SubscriptionEntity implements EntityWithId<UUID> {
    @Id
    @Column(name = "SUBSCRIPTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME")
    private String subscriptionName;

    // Many-to-Many relationship with User
    @ManyToMany(mappedBy = "subscriptions",fetch = FetchType.EAGER)
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
