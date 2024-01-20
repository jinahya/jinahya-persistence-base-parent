package com.github.jinahya.persistence;

import jakarta.annotation.Nullable;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @param <ID>
 * @see <a href="https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/">How to
 * implement equals and hashCode using the JPA entity identifier (Primary Key)</a>
 */
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S101",
        "java:S119"
})
public abstract class _MappedIdentifiableEntity<ID extends Serializable> extends _MappedEntity {

    @Serial
    private static final long serialVersionUID = 3712559723307026683L;

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "id=" + getId() +
                '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof _MappedIdentifiableEntity<?> that)) {
            return false;
        }
        final ID id = getId();
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
//        return getClass().hashCode();
        return super.hashCode();
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns current value of {@link ID} attribute.
     *
     * @return current value of the {@link ID} attribute.
     */
    @Nullable
    protected abstract ID getId();
}
