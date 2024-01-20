package com.github.jinahya.persistence;

import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serial;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings({
        "java:S101"
})
public abstract class _MappedEntity
        extends __Mapped {

    @Serial
    private static final long serialVersionUID = -7175867556249812326L;

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public int hashCode() {
        final var hashCode = getClass().hashCode();
        return hashCode;
    }
}
