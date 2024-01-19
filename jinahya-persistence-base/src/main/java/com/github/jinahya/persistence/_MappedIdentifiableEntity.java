package com.github.jinahya.persistence;

import jakarta.annotation.Nullable;
import jakarta.persistence.MappedSuperclass;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@SuppressWarnings({
        "java:S101",
        "java:S119"
})
public abstract class _MappedIdentifiableEntity<Id extends Serializable>
        extends _MappedEntity {

    @Serial
    private static final long serialVersionUID = 3712559723307026683L;

    /**
     * Creates a new instance.
     */
    protected _MappedIdentifiableEntity() {
        super();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof _MappedIdentifiableEntity<?> that)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        final Id id = getId();
        return id != null && Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns current value of {@link Id} attribute.
     *
     * @return current value of the {@link Id} attribute.
     */
    @Nullable
    protected abstract Id getId();
}
