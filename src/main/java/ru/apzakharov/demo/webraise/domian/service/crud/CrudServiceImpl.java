package ru.apzakharov.demo.webraise.domian.service.crud;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import ru.apzakharov.demo.webraise.domian.mapper.ExtendedEntityMapper;
import ru.apzakharov.demo.webraise.port.ApplicationPort;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CrudServiceImpl<DOMAIN, ID extends Serializable, ENTITY extends EntityWithId<ID>> implements CrudService<DOMAIN, ID, ENTITY> {
    protected final ApplicationPort<ENTITY, ID> persistencePort;
    protected final ExtendedEntityMapper<ENTITY, ID, DOMAIN> mapper;

    @Transactional
    public ID add(DOMAIN dto) {
        ENTITY entity = mapper.dtoToEntity(dto);
        return persistencePort.create(entity);
    }

    @Transactional(readOnly = true)
    public DOMAIN get(ID id) {
        return persistencePort.get(id)
                .map(mapper::entityToDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public Optional<DOMAIN> find(ID id) {
        return persistencePort.get(id)
                .map(mapper::entityToDto);
    }

    @Transactional
    public void update(DOMAIN dto, ID id) {
        ENTITY entity = persistencePort.get(id).orElseThrow(RuntimeException::new);

        ENTITY newEntity = mapper.dtoToEntity(dto);
        BeanUtils.copyProperties(newEntity, entity);
    }

    @Transactional
    public void delete(ID id) {
        persistencePort.delete(id);
    }

    @Transactional(readOnly = true)
    public List<DOMAIN> getListByExample(Example<ENTITY> example) {
        return persistencePort.findAllByExample(example).stream().map(mapper::entityToDto).toList();
    }

    @Transactional(readOnly = true)
    public DOMAIN getByExample(Example<ENTITY> example) {
        final List<DOMAIN> dtoList = getListByExample(example);

        if (dtoList.size() > 1) {
            throw new NonUniqueResultException("More than 1 DTO for expected single result");
        }

        return dtoList.stream().findFirst()
                .orElseThrow(() -> new NoResultException("NO result for expected single result"));
    }

    @Override
    public Optional<DOMAIN> findByExample(Example<ENTITY> example) {
        final List<DOMAIN> dtoList = getListByExample(example);

        if (dtoList.size() > 1) {
            throw new NonUniqueResultException("More than 1 DTO for expected single result");
        }

        return dtoList.stream().findFirst();
    }


}