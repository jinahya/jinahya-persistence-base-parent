package com.github.jinahya.persistence;

@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEmbeddableRandomizer<E extends _MappedEmbeddable> extends __MappedRandomizer<E> {

    protected _MappedEmbeddableRandomizer(final Class<E> embeddableClass) {
        super(embeddableClass);
        this.embeddableClass = mappedClass;
    }

    protected final Class<E> embeddableClass;
}
