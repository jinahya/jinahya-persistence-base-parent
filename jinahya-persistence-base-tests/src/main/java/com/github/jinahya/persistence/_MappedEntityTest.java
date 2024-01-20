package com.github.jinahya.persistence;

/**
 * An abstract base class for testing subclasses of {@link _MappedEntity} class.
 *
 * @param <E> subclass type parameter
 */
@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEntityTest<E extends _MappedEntity> extends __MappedTest<E> {

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    protected _MappedEntityTest(final Class<E> entityClass) {
        super(entityClass);
        this.entityClass = mappedClass;
    }

    protected E newEntityInstance() {
        return newMappedInstance();
    }

    protected final Class<E> entityClass;
}
