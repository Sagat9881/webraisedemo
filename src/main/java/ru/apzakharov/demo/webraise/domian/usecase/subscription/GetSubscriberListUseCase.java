package ru.apzakharov.demo.webraise.domian.usecase.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.subscription.GetSubscriberListException;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.domian.usecase.UseCase;
import ru.apzakharov.demo.webraise.port.repository.subscription.SubscriptionEntity;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GetSubscriberListUseCase implements UseCase {
    private final CrudService<Subscription, UUID, SubscriptionEntity> crudService;

    public List<Subscription> getSubscriberList(UUID userId) {
        try {
            final UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);
            final Set<UserEntity> usersForExample = Set.of(userEntity);

            final SubscriptionEntity entityForExample = new SubscriptionEntity();
            entityForExample.setUsers(usersForExample);

            return crudService.getListByExample(Example.of(entityForExample));
        } catch (Exception e) {
            throw new GetSubscriberListException(e);
        }
    }
}

