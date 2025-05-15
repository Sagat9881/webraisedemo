package ru.apzakharov.demo.webraise.adapter.controller.user.dto;

import lombok.Data;
import ru.apzakharov.demo.webraise.adapter.controller.subscription.dto.SubscriptionDTO;

import java.util.Set;

@Data
public class UserDTO {
    private String name;
    private String email;
    private Set<SubscriptionDTO> subscriptions;
}
