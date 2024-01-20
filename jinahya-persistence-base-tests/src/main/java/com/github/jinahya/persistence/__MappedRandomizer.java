package com.github.jinahya.persistence;

import java.util.Objects;

@SuppressWarnings({
        "java:S101"
})
public abstract class __MappedRandomizer<E extends __Mapped> {

    protected __MappedRandomizer(final Class<E> mappedClass) {
        super();
        this.mappedClass = Objects.requireNonNull(mappedClass, "entityClass is null");
    }

    public abstract E randomize();

    final Class<E> mappedClass;
}
