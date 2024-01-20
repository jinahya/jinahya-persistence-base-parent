package com.github.jinahya.persistence;

import nl.jqno.equalsverifier.ConfiguredEqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Test;

import java.util.Objects;

abstract class __MappedTest<M extends __Mapped> {

    __MappedTest(final Class<M> mappedClass) {
        super();
        this.mappedClass = Objects.requireNonNull(mappedClass, "mappedClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    protected void equalsContract() {
        equalsVerifier(
                equalsVerifier().forClass(mappedClass)
        ).verify();
    }

    protected ConfiguredEqualsVerifier equalsVerifier() {
        return EqualsVerifier.simple();
    }

    protected SingleTypeEqualsVerifierApi<M> equalsVerifier(final SingleTypeEqualsVerifierApi<M> equalsVerifier) {
        return equalsVerifier;
    }

    // ----------------------------------------------------------------------------------------------------- mappedClass
    final M newMappedInstance() {
        try {
            final var constructor = mappedClass.getDeclaredConstructor();
            if (!constructor.canAccess(null)) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to create a new instance of " + mappedClass, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<M> mappedClass;
}
