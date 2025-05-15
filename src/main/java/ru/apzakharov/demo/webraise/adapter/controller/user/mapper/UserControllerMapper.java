package ru.apzakharov.demo.webraise.adapter.controller.user.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.adapter.controller.ControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.subscription.mapper.SubscriptionControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.user.dto.UserDTO;
import ru.apzakharov.demo.webraise.domian.mapper.SubscriptionDomainMapper;
import ru.apzakharov.demo.webraise.domian.model.User;

import static org.mapstruct.InjectionStrategy.SETTER;

@Component
@Mapper(componentModel = "spring", uses = SubscriptionControllerMapper.class, injectionStrategy = SETTER)
public interface UserControllerMapper extends ControllerMapper<UserDTO, User> {
}
