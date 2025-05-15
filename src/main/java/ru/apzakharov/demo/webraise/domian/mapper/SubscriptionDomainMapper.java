package ru.apzakharov.demo.webraise.domian.mapper;

import org.mapstruct.Mapper;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.port.repository.subscription.SubscriptionEntity;

import java.util.UUID;

import static org.mapstruct.InjectionStrategy.SETTER;

@Mapper(componentModel = "spring", uses = UserDomainMapper.class, injectionStrategy = SETTER)
public interface SubscriptionDomainMapper extends ExtendedEntityMapper<SubscriptionEntity, UUID, Subscription> {

}
