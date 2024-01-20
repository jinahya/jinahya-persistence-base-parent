package context1;

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
class User
        extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 3248125066407302146L;

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public String toString() {
        return super.toString() + '{' +
                "name=" + name +
                '}';
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotBlank
    @Basic(optional = false)
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    private Long name;
}
