package com.github.jinahya.persistence;

@SuppressWarnings({
        "java:S101"
})
public final class _MappedEntityRandomizerUtils {

    public static <E extends _MappedEntity> E newRandomizedInstanceOf(final Class<E> entityClass) {
        return __MappedRandomizerUtils.newRandomizedInstanceOf(entityClass);
    }

    private _MappedEntityRandomizerUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
