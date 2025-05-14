package ru.apzakharov.demo.webraise.adapter.controller.user.mapper;

import org.mapstruct.Mapper;
import ru.apzakharov.demo.webraise.adapter.controller.ControllerMapper;
import ru.apzakharov.demo.webraise.adapter.controller.user.dto.UserDTO;
import ru.apzakharov.demo.webraise.domian.model.User;

@Mapper(componentModel = "spring")
public interface UserControllerMapper extends ControllerMapper<UserDTO, User> {
}
