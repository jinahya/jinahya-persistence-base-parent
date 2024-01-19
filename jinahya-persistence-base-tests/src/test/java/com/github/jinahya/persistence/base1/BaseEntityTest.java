package com.github.jinahya.persistence.base1;

import com.github.jinahya.persistence._MappedEntity;
import com.github.jinahya.persistence._MappedEntityDynamicTests;
import com.github.jinahya.persistence._MappedIdentifiableEntityTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.beans.IntrospectionException;
import java.util.stream.Stream;

@SuppressWarnings({
        "java:S2699"
})
abstract class BaseEntityTest<E extends BaseEntity>
        extends _MappedIdentifiableEntityTest<E, Long> {

    protected BaseEntityTest(final Class<E> entityClass) {
        super(entityClass);
    }

    @DisplayName("toString() should not blank")
    @TestFactory
    DynamicTest _NotBlank_ToString() {
        return _MappedEntityDynamicTests.toStringShouldNotBlank(entityClass, this::newEntityInstance);
    }

    @DisplayName("accessors should return")
    @TestFactory
    Stream<DynamicTest> _shouldReturn_Accessors() throws IntrospectionException {
        return _MappedEntityDynamicTests.accessorsShouldReturn(
                entityClass,
                this::newEntityInstance,
                (d, m) -> _MappedEntity.class.isAssignableFrom(m.getDeclaringClass())
        );
    }
}
