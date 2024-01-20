package com.github.jinahya.persistence;

import java.util.Objects;

abstract class __MappedTest<M extends __Mapped> {

    __MappedTest(final Class<M> mappedClass) {
        super();
        this.mappedClass = Objects.requireNonNull(mappedClass, "mappedClass is null");
    }

    final M newMappedInstance() {
        try {
            final var constructor = mappedClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to create a new instance of " + mappedClass, roe);
        }
    }

    final Class<M> mappedClass;
}
