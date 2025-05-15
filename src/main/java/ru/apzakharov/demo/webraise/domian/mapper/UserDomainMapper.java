package ru.apzakharov.demo.webraise.domian.mapper;

import org.mapstruct.Mapper;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.UUID;

import static org.mapstruct.InjectionStrategy.SETTER;

@Mapper(componentModel = "spring", uses = SubscriptionDomainMapper.class, injectionStrategy = SETTER)
public interface UserDomainMapper extends ExtendedEntityMapper<UserEntity, UUID, User> {

}
