package com.github.jinahya.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import static org.assertj.core.api.Assertions.assertThatCode;

@Slf4j
@SuppressWarnings({
        "java:S100", // ..._()
        "java:S119"
})
public abstract class _MappedIdentifiableEntityTest<E extends _MappedIdentifiableEntity<ID>, ID extends Serializable>
        extends _MappedEntityTest<E> {

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    protected _MappedIdentifiableEntityTest(final Class<E> entityClass) {
        super(entityClass);
    }

    // -------------------------------------------------------------------------------------------------------------- id
    @DisplayName("getId() should return")
    @Test
    protected void getId_DoesNotThrows_() {
        final var instance = newEntityInstance();
        assertThatCode(instance::getId).doesNotThrowAnyException();
    }

    // --------------------------------------------------------------------------------------------------------- idClass

    @DisplayName("getIdClass() does not throw")
    @Test
    protected void getIdClass_DoesNotThrow_() {
        assertThatCode(this::getIdClass).doesNotThrowAnyException();
    }

    /**
     * Returns the {@link ID} class of the {@link #entityClass}.
     *
     * @return the {@link ID} class of the {@link #entityClass}.
     */
    @SuppressWarnings({"unchecked"})
    protected Class<ID> getIdClass() {
        if (idClass != null) {
            return idClass;
        }
        for (Class<?> c = mappedClass; c != null; c = c.getSuperclass()) {
            final var genericSuperclass = c.getGenericSuperclass();
            if (!(genericSuperclass instanceof ParameterizedType parameterizedType)) {
                continue;
            }
            if (parameterizedType.getRawType() != _MappedIdentifiableEntity.class) {
                continue;
            }
            idClass = (Class<ID>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            return getIdClass();
        }
        throw new RuntimeException("unable to find the idClass");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The id class of the {@link #mappedClass}.
     */
    private Class<ID> idClass;
}
