package com.github.jinahya.persistence;

import org.junit.jupiter.api.DynamicTest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class _MappedIdentifiableEntityDynamicTests {

    public static <E extends _MappedIdentifiableEntity<?>> DynamicTest toString_NonBlank_(
            final Class<E> entityClass, final Supplier<? extends E> initializer) {
        return __MappedDynamicTests.toString_NonBlank_(entityClass, initializer);
    }

    public static <E extends _MappedIdentifiableEntity<?>> Stream<DynamicTest> accessors_DoesNotThrow_(
            final Class<E> entityClass, final Supplier<? extends E> initializer,
            final BiPredicate<? super PropertyDescriptor, ? super Method> predicate)
            throws IntrospectionException {
        return __MappedDynamicTests.accessors_DoesNotThrow_(entityClass, initializer, predicate);
    }

    private _MappedIdentifiableEntityDynamicTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
