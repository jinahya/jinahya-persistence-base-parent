package com.github.jinahya.persistence;

@SuppressWarnings({
        "java:S101"
})
public final class _MappedEmbeddableRandomizerUtils {

    public static <E extends _MappedEmbeddable> E newRandomizedInstanceOf(final Class<E> embeddableClass) {
        return __MappedRandomizerUtils.newRandomizedInstanceOf(embeddableClass);
    }

    private _MappedEmbeddableRandomizerUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
