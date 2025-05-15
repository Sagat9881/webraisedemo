package ru.apzakharov.demo.webraise.domian.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.domian.mapper.ExtendedEntityMapper;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudServiceImpl;
import ru.apzakharov.demo.webraise.port.ApplicationPort;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.UUID;

@Component
public class UserDomainService extends CrudServiceImpl<User, UUID, UserEntity> {

    @Autowired
    public UserDomainService(ApplicationPort<UserEntity, UUID> persistencePort,
                             ExtendedEntityMapper<UserEntity, UUID, User> mapper) {
        super(persistencePort, mapper);
    }
}
