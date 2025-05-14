package ru.apzakharov.demo.webraise.domian.usecase.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.AddSubscriptionException;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.port.repository.subscription.entity.SubscriptionEntity;
import ru.apzakharov.demo.webraise.port.repository.user.entity.UserEntity;

import java.util.UUID;

@Component
@Scope("request")
@RequiredArgsConstructor
public class AddSubscriptionUseCase {

    private final CrudService<User, UUID, UserEntity> userCrudService;
    private final CrudService<Subscription, UUID, SubscriptionEntity> subscriptionCrudService;

    void addSubscription(UUID userId, String subscriptionName) {
        try {
            final User user = userCrudService.get(userId);
            final Subscription subscriptionForAdd = subscriptionCrudService.getByExample(
                    Example.of(SubscriptionEntity.builder().subscriptionName(subscriptionName).build()) );

            user.getSubscriptions().add(subscriptionForAdd);
            userCrudService.update(user, userId);
        } catch (Exception e) {
            throw new AddSubscriptionException(e);
        }
    }
}
