package ru.apzakharov.demo.webraise.port.repository.user.entity;

import org.springframework.stereotype.Repository;
import ru.apzakharov.demo.webraise.port.repository.BaseJpaRepository;

import java.util.UUID;

@Repository
public interface UserRepository extends BaseJpaRepository<UUID, UserEntity> {

}
