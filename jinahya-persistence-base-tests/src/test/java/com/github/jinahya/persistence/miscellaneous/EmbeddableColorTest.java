package com.github.jinahya.persistence.miscellaneous;

import com.github.jinahya.persistence._MappedEmbeddableTest;

abstract class EmbeddableColorTest<C extends EmbeddableColor> extends _MappedEmbeddableTest<C> {

    protected EmbeddableColorTest(final Class<C> colorClass) {
        super(colorClass);
    }
}
