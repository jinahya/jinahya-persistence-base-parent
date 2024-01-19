package com.github.jinahya.persistence;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@Getter
class BaseUser1
        extends BaseEntity1 {

    @Serial
    private static final long serialVersionUID = 3248125066407302146L;

    @NotBlank
    @Basic(optional = false)
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    private Long name;
}
