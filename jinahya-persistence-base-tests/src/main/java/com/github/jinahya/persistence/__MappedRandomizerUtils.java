package com.github.jinahya.persistence;

import java.util.Objects;

@SuppressWarnings({
        "java:S101"
})
final class __MappedRandomizerUtils {

    static <M extends __Mapped> M newRandomizedInstanceOf(final Class<M> mappedClass) {
        Objects.requireNonNull(mappedClass, "mappedClass is null");
        try {
            final var randomizerClass = Class.forName(mappedClass.getName() + "Randomizer")
                    .asSubclass(__MappedRandomizer.class);
            final var constructor = randomizerClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            final var instance = constructor.newInstance();
            @SuppressWarnings({"unchecked"}) final var value = (M) instance.randomize();
            return value;
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("unable to find/initialize randomizer for " + mappedClass, roe);
        }
    }

    private __MappedRandomizerUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
