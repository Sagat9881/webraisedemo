package ru.apzakharov.demo.webraise.adapter.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.apzakharov.demo.webraise.adapter.ApplicationAdapter;
import ru.apzakharov.demo.webraise.adapter.controller.ControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.user.dto.UserDTO;
import ru.apzakharov.demo.webraise.domian.model.User;
import ru.apzakharov.demo.webraise.domian.usecase.user.CreateUserUseCase;
import ru.apzakharov.demo.webraise.domian.usecase.user.GetUserUseCase;
import ru.apzakharov.demo.webraise.domian.usecase.user.UpdateUserUseCase;
import ru.apzakharov.demo.webraise.domian.usecase.user.UserDeleteUseCase;

import java.util.UUID;

@RestController("/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Контроллер для работы с пользователями")
public class UserController implements ApplicationAdapter {
    private final ControllerMapper<UserDTO, User> mapper;

    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserDeleteUseCase deleteUserUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id", tags = "User")
    public UserDTO getUser(@PathVariable("id") UUID uuid) {
        return mapper.toDto(
                getUserUseCase.getUser(uuid)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление пользователя по id", tags = "User")
    public UserDTO updateUser(@PathVariable UUID id, @RequestBody UserDTO updatedUser) {
        updateUserUseCase.updateUser(mapper.toDomain(updatedUser), id);
        return updatedUser;
    }

    @PostMapping
    @Operation(summary = "Создание нового пользователя", tags = "User")
    public UUID createUser(@RequestBody UserDTO user) {
        return createUserUseCase.createUser(mapper.toDomain(user));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", tags = "User")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
