package com.github.jinahya.persistence.miscellaneous;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestFactory;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
abstract class EmbeddableArgbTest<C extends EmbeddableArgb> extends EmbeddableColorTest<C> {

    static IntStream componentStream() {
        return IntStream.rangeClosed(0, 255);
    }

    EmbeddableArgbTest(final Class<C> colorClass) {
        super(colorClass);
    }

    DynamicTest getComponentDynamicTest(final String componentName) throws NoSuchMethodException {
        final var name = "get" + StringUtils.capitalize(componentName);
        final var getter = embeddableClass.getDeclaredMethod(name);
        return DynamicTest.dynamicTest(name + "()", () -> {
            final var instance = newEmbeddableInstance();
            assertThatCode(() -> {
                final var component = (int) getter.invoke(instance);
                assertThat(component).isBetween(EmbeddableArgb.MIN_COMPONENT, EmbeddableArgb.MAX_COMPONENT);
            }).doesNotThrowAnyException();
        });
    }

    Stream<DynamicTest> setComponentDynamicTestStream(final String componentName) throws NoSuchMethodException {
        final var name = "set" + StringUtils.capitalize(componentName);
        final var setter = embeddableClass.getMethod(name, int.class);
        return IntStream.range(0, 128)
                .map(i -> ThreadLocalRandom.current().nextInt())
                .mapToObj(c -> DynamicTest.dynamicTest(name + '(' + c + ')', () -> {
                    final var instance = newEmbeddableInstance();
                    assertThatCode(() -> {
                        setter.invoke(instance, c);
                    }).doesNotThrowAnyException();
                }));
    }

    Stream<DynamicTest> setComponentAndGetComponentDynamicTestStream(final String componentName)
            throws NoSuchMethodException {
        final var getterName = "get" + StringUtils.capitalize(componentName);
        final var getter = embeddableClass.getDeclaredMethod(getterName);
        final var setterName = "set" + StringUtils.capitalize(componentName);
        final var setter = embeddableClass.getMethod(setterName, int.class);
        return IntStream.range(0, 128)
                .map(i -> ThreadLocalRandom.current().nextInt())
                .mapToObj(c -> DynamicTest.dynamicTest(
                        setterName + '(' + c + ") -> " + getterName + "()" + (c & EmbeddableArgb.MAX_COMPONENT),
                        () -> {
                            final var instance = newEmbeddableInstance();
                            assertThatCode(() -> {
                                setter.invoke(instance, c);
                            }).doesNotThrowAnyException();
                            assertThat(getter.invoke(instance)).isEqualTo(c & EmbeddableArgb.MAX_COMPONENT);
                        }));
    }

    abstract class ComponentTest {

        ComponentTest(final String componentName) {
            super();
            this.componentName = Objects.requireNonNull(componentName, "componentName is null");
        }

        @DisplayName("getComponent()[0..255]")
        @TestFactory
        DynamicTest getComponent__() throws NoSuchMethodException {
            return getComponentDynamicTest(componentName);
        }

        @DisplayName("setComponent(component)")
        @TestFactory
        Stream<DynamicTest> setComponent__() throws NoSuchMethodException {
            return setComponentDynamicTestStream(componentName);
        }

        @DisplayName("setComponent(component) -> getComponent()")
        @TestFactory
        Stream<DynamicTest> getComponent_ShouldEqual_SetComponent() throws NoSuchMethodException {
            return setComponentAndGetComponentDynamicTestStream(componentName);
        }

        private final String componentName;
    }

    @DisplayName("alpha")
    @Nested
    class AlphaTest extends ComponentTest {

        AlphaTest() {
            super("alpha");
        }
    }

    @DisplayName("red")
    @Nested
    class RedTest extends ComponentTest {

        RedTest() {
            super("red");
        }
    }

    @DisplayName("green")
    @Nested
    class GreenTest extends ComponentTest {

        GreenTest() {
            super("green");
        }
    }

    @DisplayName("blue")
    @Nested
    class BlueTest extends ComponentTest {

        BlueTest() {
            super("blue");
        }
    }
}
