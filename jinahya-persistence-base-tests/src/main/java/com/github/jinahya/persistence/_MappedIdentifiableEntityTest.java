package com.github.jinahya.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@SuppressWarnings({
        "java:S119"
})
public abstract class _MappedIdentifiableEntityTest<E extends _MappedIdentifiableEntity<Id>, Id extends Serializable>
        extends _MappedEntityTest<E> {

    /**
     * Creates a new instance for testing specified entity class.
     *
     * @param entityClass the entity class to test.
     */
    protected _MappedIdentifiableEntityTest(final Class<E> entityClass) {
        super(entityClass);
    }

    /**
     * Returns the id class of the {@link #entityClass}.
     *
     * @return the id class of the {@link #entityClass}.
     */
    @SuppressWarnings({"unchecked"})
    protected Class<Id> getIdClass() {
        if (idClass != null) {
            return idClass;
        }
        for (Class<?> c = entityClass; c != null; c = c.getSuperclass()) {
            final var genericSuperclass = c.getGenericSuperclass();
            if (genericSuperclass != _MappedIdentifiableEntity.class) {
                continue;
            }
            idClass = (Class<Id>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            return idClass;
        }
        throw new RuntimeException("unable to find the idClass");
    }

    /**
     * The id class of the {@link #entityClass}.
     */
    private Class<Id> idClass;
}
