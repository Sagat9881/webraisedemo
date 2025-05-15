package ru.apzakharov.demo.webraise.adapter.controller.subscription.mapper;

import org.mapstruct.Mapper;
import ru.apzakharov.demo.webraise.adapter.controller.ControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.subscription.dto.SubscriptionDTO;
import ru.apzakharov.demo.webraise.adapter.controller.user.mapper.UserControllerMapper;
import ru.apzakharov.demo.webraise.domian.model.Subscription;

import static org.mapstruct.InjectionStrategy.SETTER;

@Mapper(componentModel = "spring", uses = UserControllerMapper.class, injectionStrategy = SETTER)
public interface SubscriptionControllerMapper
        extends ControllerMapper<SubscriptionDTO, Subscription> {
}
