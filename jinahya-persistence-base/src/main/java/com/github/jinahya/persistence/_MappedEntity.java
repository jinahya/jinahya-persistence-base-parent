package com.github.jinahya.persistence;

import jakarta.persistence.MappedSuperclass;

import java.io.Serial;

@MappedSuperclass
@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEntity
        extends __Mapped {

    @Serial
    private static final long serialVersionUID = -7175867556249812326L;

    /**
     * Creates a new instance.
     */
    protected _MappedEntity() {
        super();
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
