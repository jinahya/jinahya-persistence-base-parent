package com.github.jinahya.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DynamicTest;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
@SuppressWarnings({
        "java:S101", // class _Mapped...
        "java:S5960"
})
final class __MappedDynamicTests {

    static <M extends __Mapped> DynamicTest toString_NonBlank_(
            final Class<M> mappedClass, final Supplier<? extends M> initializer) {
        Objects.requireNonNull(mappedClass, "mappedClass is null");
        Objects.requireNonNull(initializer, "initializer is null");
        final M instance = Objects.requireNonNull(initializer.get(), "null supplied by " + initializer);
        return DynamicTest.dynamicTest(
                String.format("[%1$s].toString() should return a non-blank", instance),
                () -> {
                    final var string = initializer.toString();
                    assertThat(string)
                            .as("toString() of %s", instance)
                            .isNotBlank();
                }
        );
    }

    @SuppressWarnings({"java:S3011"})
    static <M extends __Mapped> Stream<DynamicTest> accessors_DoesNotThrow_(
            final Class<M> mappedClass, final Supplier<? extends M> initializer,
            final BiPredicate<? super PropertyDescriptor, ? super Method> predicate)
            throws IntrospectionException {
        Objects.requireNonNull(mappedClass, "mappedClass is null");
        Objects.requireNonNull(initializer, "initializer is null");
        Objects.requireNonNull(predicate, "predicate is null");
        final M instance = Objects.requireNonNull(initializer.get(), "null supplied by " + initializer);
        final var beanInfo = Introspector.getBeanInfo(mappedClass);
        return Arrays.stream(beanInfo.getPropertyDescriptors()).map(d -> {
            return DynamicTest.dynamicTest(Objects.toString(d.getDisplayName()), () -> {
                final var reader = d.getReadMethod();
                if (reader == null) {
                    log.debug("no reader on " + d);
                    return;
                }
                if (!predicate.test(d, reader)) {
                    log.debug("skipping {}", reader);
                }
                if (!reader.canAccess(instance)) {
                    reader.setAccessible(true);
                }
                final Object[] value = new Object[1];
                assertThatCode(() -> value[0] = reader.invoke(instance)).doesNotThrowAnyException();
                final var writer = d.getWriteMethod();
                if (writer == null) {
                    log.debug("no writer on {}", d);
                    return;
                }
                if (!writer.canAccess(instance)) {
                    writer.setAccessible(true);
                }
                assertThatCode(() -> writer.invoke(instance, value[0])).doesNotThrowAnyException();
            });
        });
    }

    private __MappedDynamicTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
