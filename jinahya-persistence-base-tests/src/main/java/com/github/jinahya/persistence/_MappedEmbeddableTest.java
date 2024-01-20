package com.github.jinahya.persistence;

public abstract class _MappedEmbeddableTest<E extends _MappedEmbeddable> extends __MappedTest<E> {

    protected _MappedEmbeddableTest(final Class<E> embeddableClass) {
        super(embeddableClass);
        this.embeddableClass = super.mappedClass;
    }

    protected E newEmbeddableInstance() {
        return newMappedInstance();
    }

    protected final Class<E> embeddableClass;
}
