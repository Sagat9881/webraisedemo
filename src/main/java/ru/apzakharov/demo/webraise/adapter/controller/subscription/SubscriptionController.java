package ru.apzakharov.demo.webraise.adapter.controller.subscription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.apzakharov.demo.webraise.adapter.ApplicationAdapter;
import ru.apzakharov.demo.webraise.adapter.controller.ControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.subscription.dto.SubscriptionDTO;
import ru.apzakharov.demo.webraise.domian.model.Subscription;
import ru.apzakharov.demo.webraise.domian.usecase.subscription.AddSubscriptionUseCase;
import ru.apzakharov.demo.webraise.domian.usecase.subscription.DeleteSubscriptionUseCase;
import ru.apzakharov.demo.webraise.domian.usecase.subscription.GetSubscriberListUseCase;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Subscription", description = "Контроллер для работы с подписками пользователя")
public class SubscriptionController implements ApplicationAdapter {
    private final ControllerMapper<SubscriptionDTO, Subscription> mapper;

    private final AddSubscriptionUseCase addUseCase;
    private final DeleteSubscriptionUseCase deleteUseCase;
    private final GetSubscriberListUseCase getUseCase;

    @GetMapping("/users/{userId}/subscriptions")
    @Operation(summary = "Получение подписок пользователя по userId", tags = "Subscription")
    public List<SubscriptionDTO> getUsersSubscription(@PathVariable("userId") UUID userId) {
        return getUseCase.getSubscriberList(userId)
                .stream().map(mapper::toDto).toList();
    }


    @PostMapping("/users/{userId}/subscriptions")
    @Operation(summary = "Добавление пользователю новой подписки", tags = "Subscription")
    public void addSubscription(@RequestBody SubscriptionDTO subscription, @PathVariable("userId") UUID userId) {
        addUseCase.addSubscription(userId, subscription.getSubscriptionName());
    }


    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    @Operation(summary = "Удаление подписки у пользователя", tags = "Subscription")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId,
                                           @PathVariable("subscriptionId") UUID subscriptionId) {
        deleteUseCase.deleteSubscription(userId, subscriptionId);
        return ResponseEntity.noContent().build();
    }

}
