package ru.apzakharov.demo.webraise.domian.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.domian.mapper.ExtendedEntityMapper;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudServiceImpl;
import ru.apzakharov.demo.webraise.port.ApplicationPort;
import ru.apzakharov.demo.webraise.port.repository.subscription.SubscriptionEntity;

import java.util.UUID;

@Component
public class SubscriptionDomainService extends CrudServiceImpl<Subscription, UUID, SubscriptionEntity> {

    @Autowired
    public SubscriptionDomainService(ApplicationPort<SubscriptionEntity, UUID> persistencePort,
                                     ExtendedEntityMapper<SubscriptionEntity, UUID, Subscription> mapper) {
        super(persistencePort, mapper);
    }
}
