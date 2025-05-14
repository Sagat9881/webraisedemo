package ru.apzakharov.demo.webraise.domian.service.crud;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.apzakharov.demo.webraise.domian.mapper.ExtendedEntityMapper;
import ru.apzakharov.demo.webraise.port.repository.BaseJpaRepository;
import ru.apzakharov.demo.webraise.port.repository.EntityWithId;

import java.io.Serializable;
import java.util.List;

@Component
public class CrudServiceImpl<DOMAIN, ID extends Serializable, ENTITY extends EntityWithId<ID>> implements CrudService<DOMAIN, ID, ENTITY> {
    protected final BaseJpaRepository<ENTITY, ID> repository;
    protected final ExtendedEntityMapper<ENTITY, ID, DOMAIN> mapper;

    protected CrudServiceImpl(@Autowired BaseJpaRepository<ENTITY, ID> repository,
                              @Autowired ExtendedEntityMapper<ENTITY, ID, DOMAIN> mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Transactional
    public ID add(DOMAIN dto) {
        ENTITY entity = mapper.dtoToEntity(dto);
        return repository.save(entity).getId();
    }

    @Transactional(readOnly = true)
    public DOMAIN get(ID id) {
        return repository.findById(id)
                .map(mapper::entityToDto)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void update(DOMAIN dto, ID id) {
        ENTITY entity = repository.findById(id).orElseThrow(RuntimeException::new);

        ENTITY newEntity = mapper.dtoToEntity(dto);
        BeanUtils.copyProperties(newEntity, entity);
    }

    @Transactional
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DOMAIN> getListByExample(Example<ENTITY> example) {
        return repository.findAll(example).stream().map(mapper::entityToDto).toList();
    }

    @Transactional(readOnly = true)
    public DOMAIN getByExample(Example<ENTITY> example) {
        final List<DOMAIN> dtoList = repository.findAll(example)
                .stream()
                .map(mapper::entityToDto)
                .toList();

        if (dtoList.size() > 1) {
            throw new NonUniqueResultException("More than 1 DTO for expected single result");
        }

        return dtoList.stream().findFirst()
                .orElseThrow(() -> new NoResultException("NO result for expected single result"));
    }


}