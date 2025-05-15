package ru.apzakharov.demo.webraise.port.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.apzakharov.demo.webraise.port.ApplicationPort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<ID extends Serializable, T extends EntityWithId<ID>> extends
        ApplicationPort<T, ID>,
        QuerydslPredicateExecutor<T>,
        JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>,
        QueryDslPredicateExecutorFixRepository<T> {

    @Override
    default Optional<T> get(ID userId) {
        return this.findById(userId);
    }

    @Override
    default ID create(T T) {
        return this.save(T).getId();
    }

    @Override
    default List<T> findAllByExample(Example<T> example) {
        return this.findAll(example);
    }

    @Override
    default void delete(ID ID) {
        this.deleteById(ID);
    }

}
