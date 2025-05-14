package ru.apzakharov.demo.webraise.domian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface ExtendedEntityMapper<E extends Persistable<I>, I extends Serializable, D> {

    @Mapping(target = "id", ignore = true)
    E dtoToEntity(D dto);

    D entityToDto(E entity);


}