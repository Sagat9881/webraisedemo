package ru.apzakharov.demo.webraise.port.repository.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;
import ru.apzakharov.demo.webraise.port.repository.subscription.SubscriptionEntity;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "USERS", schema = "WEBRISE_DEMO")
@NoArgsConstructor
public class UserEntity implements EntityWithId<UUID> {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;

    // Many-to-Many relationship with Subscription
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USERS_SUBSCRIPTIONS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_ID"))
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
