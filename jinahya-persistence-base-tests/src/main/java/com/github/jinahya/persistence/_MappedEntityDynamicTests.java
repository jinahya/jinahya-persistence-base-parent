package com.github.jinahya.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DynamicTest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
@SuppressWarnings({
        "java:S101" // class _Mapped...
})
public final class _MappedEntityDynamicTests {

    public static <E extends _MappedEntity> DynamicTest toString_NonBlank_(
            final Class<E> entityClass, final Supplier<? extends E> initializer) {
        return __MappedDynamicTests.toString_NonBlank_(entityClass, initializer);
    }

    public static <E extends _MappedEntity> Stream<DynamicTest> accessors_DoesNothrow_(
            final Class<E> entityClass, final Supplier<? extends E> initializer,
            final BiPredicate<? super PropertyDescriptor, ? super Method> predicate)
            throws IntrospectionException {
        return __MappedDynamicTests.accessors_DoesNotThrow_(entityClass, initializer, predicate);
    }

    private _MappedEntityDynamicTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
