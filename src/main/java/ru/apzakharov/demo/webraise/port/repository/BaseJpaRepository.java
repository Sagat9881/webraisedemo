package ru.apzakharov.demo.webraise.port.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.apzakharov.demo.webraise.port.ApplicationPort;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends QuerydslPredicateExecutor<T>, JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QueryDslPredicateExecutorFixRepository<T>, ApplicationPort {

}
