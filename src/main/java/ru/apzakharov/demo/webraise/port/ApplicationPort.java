package ru.apzakharov.demo.webraise.port;

import org.springframework.data.domain.Example;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * representation of DRIVEN applications port with crud
 */
public interface ApplicationPort<DOMAIN, KEY extends Serializable> {

    Optional<DOMAIN> get(KEY dto);

    KEY create(DOMAIN key);

    List<DOMAIN> findAllByExample(Example<DOMAIN> example);

    void delete(KEY key);
}
