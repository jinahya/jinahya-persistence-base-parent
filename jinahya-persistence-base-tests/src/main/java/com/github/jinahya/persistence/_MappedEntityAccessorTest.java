package com.github.jinahya.persistence;

import com.github.jinahya.persistence._MappedEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * An abstract base class for testing subclasses of {@link _MappedEntity} class.
 *
 * @param <E> subclass type parameter
 */
@SuppressWarnings({
        "java:S101"
})
public class _MappedEntityAccessorTest<E extends _MappedEntity> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    public _MappedEntityAccessorTest(final Class<E> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("toString()!blank")
    @Test
    void toString_NotBlank_() {
        // ------------------------------------------------------------------------------------------------------- given
        final E instance = newEntityInstance();
        // -------------------------------------------------------------------------------------------------------- when
        final String string = newEntityInstance().toString();
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(string).isNotBlank();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("setXxx(getXxx())")
    @Test
    @SuppressWarnings({
            "java:S3011"
    })
    void accessors_() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        final E instance = newEntityInstance();
        for (Class<?> c = entityClass; c != null; c = c.getSuperclass()) {
            final var info = Introspector.getBeanInfo(c);
            for (var descriptor : info.getPropertyDescriptors()) {
                final var reader = descriptor.getReadMethod();
                if (reader == null) {
                    continue;
                }
                if (!reader.canAccess(instance)) {
                    reader.setAccessible(true);
                }
                final var value = reader.invoke(instance);
                final var writer = descriptor.getWriteMethod();
                if (writer == null) {
                    continue;
                }
                if (!writer.canAccess(instance)) {
                    writer.setAccessible(true);
                }
                assertThatCode(() -> {
                    writer.invoke(instance, value);
                }).doesNotThrowAnyException();
            }
        }
        // ------------------------------------------------------------------------------------------------------- given
        // -------------------------------------------------------------------------------------------------------- when
        final String string = newEntityInstance().toString();
        // -------------------------------------------------------------------------------------------------------- then
        assertThat(string).isNotBlank();
    }

    // ----------------------------------------------------------------------------------------------------- entityClass

    /**
     * Creates a new instance of {@link #entityClass},
     *
     * @return a new instance of the {@link #entityClass},
     */
    protected E newEntityInstance() {
        try {
            return entityClass.getDeclaredConstructor().newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to create a new instance of " + entityClass, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The entity class to test.
     */
    protected final Class<E> entityClass;
}
