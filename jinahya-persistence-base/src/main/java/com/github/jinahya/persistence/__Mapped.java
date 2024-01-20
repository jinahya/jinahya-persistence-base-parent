package com.github.jinahya.persistence;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;

@MappedSuperclass
@SuppressWarnings({
        "java:S101"
})
abstract class __Mapped
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 3017361413321767365L;

    /**
     * Creates a new instance.
     */
    __Mapped() {
        super();
    }
}
