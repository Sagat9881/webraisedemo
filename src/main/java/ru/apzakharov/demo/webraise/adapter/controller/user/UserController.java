package ru.apzakharov.demo.webraise.adapter.controller.user;

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
public class UserController implements ApplicationAdapter {
    private final ControllerMapper<UserDTO, User> mapper;

    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final UserDeleteUseCase deleteUserUseCase;

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") UUID uuid) {
        return mapper.toDto(
                getUserUseCase.getUser(uuid)
        );
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable UUID id, @RequestBody UserDTO updatedUser) {
        updateUserUseCase.updateUser(mapper.toDomain(updatedUser), id);
        return updatedUser;
    }

    @PostMapping
    public UUID createUser(@RequestBody UserDTO user) {
        return createUserUseCase.createUser(mapper.toDomain(user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
