package ru.apzakharov.demo.webraise.port.repository.subscription;

import org.springframework.stereotype.Repository;
import ru.apzakharov.demo.webraise.port.repository.BaseJpaRepository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends BaseJpaRepository<UUID, SubscriptionEntity> {
}
