package ru.apzakharov.demo.webraise.adapter.controller.subscription.dto;

import lombok.Data;
import ru.apzakharov.demo.webraise.adapter.controller.user.dto.UserDTO;

import java.util.Set;

@Data
public class SubscriptionDTO {
    private String subscriptionName;
    private Set<UserDTO> users;
}
