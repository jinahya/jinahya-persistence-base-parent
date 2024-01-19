package com.github.jinahya.persistence;

import com.github.jinahya.persistence._MappedIdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

abstract class BaseEntity1
        extends _MappedIdentifiableEntity<Long> {

    static final String COLUMN_NAME_ID = "id";

    @Override
    protected Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ID, nullable = false, insertable = false, updatable = false)
    private Long id;
}
