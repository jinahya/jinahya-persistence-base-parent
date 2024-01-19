package com.github.jinahya.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.beans.IntrospectionException;
import java.util.stream.Stream;

@SuppressWarnings({
        "java:S2699"
})
abstract class BaseEntity1Test<E extends BaseEntity1>
        extends _MappedIdentifiableEntityTest<E, Long> {

    BaseEntity1Test(final Class<E> entityClass) {
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
                (d, m) -> __MappedEntity.class.isAssignableFrom(m.getDeclaringClass())
        );
    }
}
