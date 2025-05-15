package ru.apzakharov.demo.webraise.domian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public interface ExtendedEntityMapper<E extends Persistable<I>, I extends Serializable, D> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);


}