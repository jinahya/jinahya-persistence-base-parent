package com.github.jinahya.persistence.miscellaneous;

import com.github.jinahya.persistence._MappedEmbeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class EmbeddableColor extends _MappedEmbeddable {

    @Serial
    private static final long serialVersionUID = 4748731173131345609L;
}
