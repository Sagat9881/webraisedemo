package ru.apzakharov.demo.webraise.domian.usecase.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.subscription.AddSubscriptionException;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.port.repository.subscription.SubscriptionEntity;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class AddSubscriptionUseCase {

    private final CrudService<User, UUID, UserEntity> userCrudService;
    private final CrudService<Subscription, UUID, SubscriptionEntity> subscriptionCrudService;

    public void addSubscription(UUID userId, String subscriptionName) {
        try {
            final User user = userCrudService.get(userId);
            final Subscription subscriptionForAdd =
                    getByExample(subscriptionName)
                            .orElseGet(createNewSubscription(subscriptionName));

            user.getSubscriptions().add(subscriptionForAdd);

            userCrudService.update(user, userId);
        } catch (Exception e) {
            throw new AddSubscriptionException(e);
        }
    }

    private Supplier<Subscription> createNewSubscription(String subscriptionName) {
        return () -> {
            final Subscription dto = Subscription.builder()
                    .subscriptionName(subscriptionName)
                    .build();
            final UUID id = subscriptionCrudService.add(dto);
            dto.setId(id);
            return dto;
        };
    }

    private Optional<Subscription> getByExample(String subscriptionName) {
        final SubscriptionEntity probe = new SubscriptionEntity();
        probe.setSubscriptionName(subscriptionName);

        return subscriptionCrudService
                .findByExample(Example.of(probe));
    }
}
