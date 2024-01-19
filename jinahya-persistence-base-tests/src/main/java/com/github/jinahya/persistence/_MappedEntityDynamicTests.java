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
//        "java:S101"
        "java:S5960"
})
public final class _MappedEntityDynamicTests {

    public static <E extends _MappedEntity> DynamicTest toStringShouldNotBlank(final Class<E> entityClass,
                                                                               final Supplier<? extends E> initializer) {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(initializer, "initializer is null");
        final E instance = initializer.get();
        assertThat(instance)
                .as("instance supplied by %s", initializer)
                .isNotNull();
        return DynamicTest.dynamicTest(
                String.format("[%1$s].toString() should not blank", instance),
                () -> {
                    final var string = initializer.toString();
                    assertThat(string)
                            .as("toString() of %s", instance)
                            .isNotBlank();
                }
        );
    }

    @SuppressWarnings({"java:S3011"})
    public static <E extends _MappedEntity> Stream<DynamicTest> accessorsShouldReturn(
            final Class<E> entityClass, final Supplier<? extends E> initializer,
            final BiPredicate<? super PropertyDescriptor, ? super Method> predicate)
            throws IntrospectionException {
        Objects.requireNonNull(entityClass, "entityClass is null");
        Objects.requireNonNull(initializer, "initializer is null");
        Objects.requireNonNull(predicate, "predicate is null");
        final E instance = initializer.get();
        assertThat(instance)
                .as("instance supplied by %s", initializer)
                .isNotNull();
        final var beanInfo = Introspector.getBeanInfo(entityClass);
        return Arrays.stream(beanInfo.getPropertyDescriptors())
                .map(d -> DynamicTest.dynamicTest(Objects.toString(d.getDisplayName()), () -> {
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
                }));
    }

    private _MappedEntityDynamicTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
