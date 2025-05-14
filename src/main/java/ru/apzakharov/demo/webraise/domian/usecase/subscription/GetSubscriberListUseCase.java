package ru.apzakharov.demo.webraise.domian.usecase.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.GetSubscriberListException;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.domian.usecase.UseCase;
import ru.apzakharov.demo.webraise.port.repository.subscription.entity.SubscriptionEntity;
import ru.apzakharov.demo.webraise.port.repository.user.entity.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@Scope("request")
@RequiredArgsConstructor
public class GetSubscriberListUseCase implements UseCase {
    private final CrudService<Subscription, UUID, SubscriptionEntity> crudService;

    public List<Subscription> getSubscriberList(UUID userId) {
        try {
            final Set<UserEntity> usersForExample = Set.of(UserEntity.builder().id(userId).build());

            final SubscriptionEntity entityForExample =
                    SubscriptionEntity.builder().users(usersForExample).build();

            return crudService.getListByExample(Example.of(entityForExample));
        } catch (Exception e) {
            throw new GetSubscriberListException(e);
        }
    }
}
