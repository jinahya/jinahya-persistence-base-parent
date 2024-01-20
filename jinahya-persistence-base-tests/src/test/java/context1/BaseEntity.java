package context1;

import com.github.jinahya.persistence._MappedIdentifiableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class BaseEntity
        extends _MappedIdentifiableEntity<Long> {

    @Serial
    private static final long serialVersionUID = -6008001217711482817L;

    // -------------------------------------------------------------------------------------------------------------- id
    static final String COLUMN_NAME_ID = "id";

    static final String ATTRIBUTE_NAME_ID = "id";

    // -------------------------------------------------------------------------------------------------------------- id
    @Override
    protected Long getId() {
        return id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_ID, nullable = false, insertable = false, updatable = false)
    private Long id;
}
