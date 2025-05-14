package ru.apzakharov.demo.webraise.domian.service.crud;

import org.springframework.data.domain.Example;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;

import java.io.Serializable;
import java.util.List;

public interface CrudService<DTO, ID extends Serializable, ENTITY extends EntityWithId<ID>> {
    ID add(DTO dto);

    DTO get(ID id);

    void update(DTO dto, ID id);

    void delete(ID id);

    List<DTO> getListByExample(Example<ENTITY> example);

    DTO getByExample(Example<ENTITY> example);
}
