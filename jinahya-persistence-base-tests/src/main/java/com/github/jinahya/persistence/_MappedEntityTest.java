package com.github.jinahya.persistence;

import java.util.Objects;

/**
 * An abstract base class for testing subclasses of {@link _MappedEntity} class.
 *
 * @param <E> subclass type parameter
 */
@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEntityTest<E extends _MappedEntity> {

    // ------------------------------------------------------------------------------------------ STATIC_FACTORY_METHODS

    // ----------------------------------------------------------------------------------------------------- CONSTRUCTOR

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    protected _MappedEntityTest(final Class<E> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // ----------------------------------------------------------------------------------------------------- entityClass

    /**
     * Creates a new instance of {@link #entityClass},
     *
     * @return a new instance of the {@link #entityClass},
     */
    protected E newEntityInstance() {
        try {
            final var constructor = entityClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
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
