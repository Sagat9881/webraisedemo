package ru.apzakharov.demo.webraise.domian.mapper.conventer.jpa;

import jakarta.persistence.Id;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.apzakharov.demo.webraise.application.util.ReflectionUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class IdToJpaReferenceConverterFactory implements ConverterFactory<Object, Object>, ConditionalConverter {

    private Map<Class, JpaRepository> repositoryMap;
    private Map<Class, Field> idFieldMap;
    private final ConversionService conversionService;

    private List<? extends JpaRepository> repositories;

    public IdToJpaReferenceConverterFactory(ConversionService conversionService, List<? extends JpaRepository> repositories) {
        this.conversionService = conversionService;
        this.repositories = repositories;
        init();
    }

    private void init() {
        Map<Class, JpaRepository> repositoryBuilder = new HashMap<>();
        Map<Class, Field> idFieldBuilder = new HashMap<>();
        for (JpaRepository repository : repositories) {
            Class entityClass = ReflectionUtil.getGenericParameterClass(repository.getClass(), JpaRepository.class,0);

            List<Field> ids = FieldUtils.getFieldsListWithAnnotation(entityClass, Id.class);
            if (ids.size() == 1) {
                repositoryBuilder.put(entityClass, repository);
                idFieldBuilder.put(entityClass, ids.get(0));
            }
        }
        repositoryMap = Collections.unmodifiableMap(repositoryBuilder);
        idFieldMap = Collections.unmodifiableMap(idFieldBuilder);
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (repositoryMap.get(targetType.getType()) == null) {
            return false;
        }

        return conversionService.canConvert(sourceType, new TypeDescriptor(idFieldMap.get(targetType.getType())));
    }

    @Override
    public <T> Converter<Object, T> getConverter(Class<T> targetType) {
        JpaRepository repository = repositoryMap.get(targetType);
        return new IdToJpaReferenceConverter<T>(repository, conversionService, idFieldMap.get(targetType));
    }

    private static class IdToJpaReferenceConverter<T> implements Converter<Object, T> {
        private final JpaRepository<T, Serializable> repository;
        private final ConversionService conversionService;
        private final Field idField;

        IdToJpaReferenceConverter(JpaRepository<T, Serializable> repository, ConversionService conversionService, Field idField) {
            this.repository = repository;
            this.conversionService = conversionService;
            this.idField = idField;
        }

        @Override
        public T convert(Object source) {
            if ("null".equals(source)) {
                return null;
            }

            Serializable id = (Serializable) conversionService.convert(source, idField.getType());
            T entity = null;
            if (id != null)
                entity = repository.findById(id).orElse(null);
            return entity;
        }
    }
}