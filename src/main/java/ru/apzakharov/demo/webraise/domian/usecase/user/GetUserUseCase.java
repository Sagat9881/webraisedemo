package ru.apzakharov.demo.webraise.domian.usecase.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.user.GetUserException;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.domian.usecase.UseCase;
import ru.apzakharov.demo.webraise.port.repository.user.entity.UserEntity;

import java.util.UUID;

@Component
@Scope("request")
@RequiredArgsConstructor
public class GetUserUseCase implements UseCase {

    private final CrudService<User, UUID, UserEntity> crudService;

    public User getUser(UUID uuid) {
        try {
            return crudService.get(uuid);
        } catch (Exception e) {
            throw new GetUserException(e);
        }
    }
}
