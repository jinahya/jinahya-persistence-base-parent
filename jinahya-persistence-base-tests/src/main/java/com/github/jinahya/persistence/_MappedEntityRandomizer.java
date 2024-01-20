package com.github.jinahya.persistence;

@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEntityRandomizer<E extends _MappedEntity> extends __MappedRandomizer<E> {

    protected _MappedEntityRandomizer(final Class<E> entityClass) {
        super(entityClass);
        this.entityClass = mappedClass;
    }

    protected final Class<E> entityClass;
}
