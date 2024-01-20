package com.github.jinahya.persistence.miscellaneous;

import nl.jqno.equalsverifier.ConfiguredEqualsVerifier;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

class EmbeddableArgbUnpopulatedTest extends EmbeddableArgbTest<EmbeddableArgb.Unpopulated> {

    EmbeddableArgbUnpopulatedTest() {
        super(EmbeddableArgb.Unpopulated.class);
    }
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    protected ConfiguredEqualsVerifier equalsVerifier() {
        return super.equalsVerifier();
    }

    @Override
    protected SingleTypeEqualsVerifierApi<EmbeddableArgb.Unpopulated> equalsVerifier(
            final SingleTypeEqualsVerifierApi<EmbeddableArgb.Unpopulated> equalsVerifier) {
        return super.equalsVerifier(equalsVerifier);
    }
}
