package com.github.jinahya.persistence;

import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({
        "java:S101"
})
public final class _MappedEntityTestUtils {

    public static <E extends _MappedEntity> Stream<Class<?>> classStream(final Class<E> entityClass) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        return Stream.iterate(
                entityClass,
                c -> c != __MappedEntity.class,
                Class::getSuperclass
        );
    }

    private _MappedEntityTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
