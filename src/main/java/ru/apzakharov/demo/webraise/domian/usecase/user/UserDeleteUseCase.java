package ru.apzakharov.demo.webraise.domian.usecase.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.apzakharov.demo.webraise.application.exception.user.DeleteUserException;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.service.crud.CrudService;
import ru.apzakharov.demo.webraise.domian.usecase.UseCase;
import ru.apzakharov.demo.webraise.port.repository.user.UserEntity;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDeleteUseCase implements UseCase {
    private final CrudService<User, UUID, UserEntity> crudService;

    public void deleteUser(UUID uuid) {
        try {
            crudService.delete(uuid);
        } catch (Exception e) {
            throw new DeleteUserException(e);
        }
    }
}
