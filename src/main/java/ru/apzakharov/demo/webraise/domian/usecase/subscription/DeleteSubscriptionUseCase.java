package ru.apzakharov.demo.webraise.domian.usecase.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.domian.usecase.UseCase;
import ru.apzakharov.demo.webraise.port.repository.subscription.entity.SubscriptionEntity;
import ru.apzakharov.demo.webraise.port.repository.user.entity.UserEntity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Scope("request")
@RequiredArgsConstructor
public class DeleteSubscriptionUseCase implements UseCase {

    private final CrudService<User, UUID, UserEntity> userService;
    private final CrudService<Subscription, UUID, SubscriptionEntity> subscriptionService;

    public void deleteSubscription(UUID userId, UUID subscriptionId) {
        final User user = userService.get(userId);
        final Set<Subscription> userSubscriptions = user.getSubscriptions();

        final Subscription subscriptionForDelete = subscriptionService.get(subscriptionId);
        final String subscriptionForDeleteName = subscriptionForDelete.getSubscriptionName();

        final Set<Subscription> updatedSubscriptions = userSubscriptions.stream()
                .filter(s -> subscriptionForDeleteName.equals(s.getSubscriptionName()))
                .collect(Collectors.toSet());

        user.setSubscriptions(updatedSubscriptions);

        userService.update(user, userId);
    }
}
