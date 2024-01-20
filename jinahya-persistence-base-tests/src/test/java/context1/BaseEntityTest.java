package context1;

import com.github.jinahya.persistence._MappedEntity;
import com.github.jinahya.persistence._MappedEntityDynamicTests;
import com.github.jinahya.persistence._MappedIdentifiableEntityTest;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.beans.IntrospectionException;
import java.util.stream.Stream;

@SuppressWarnings({
        "java:S2699"
})
abstract class BaseEntityTest<E extends BaseEntity>
        extends _MappedIdentifiableEntityTest<E, Long> {

    protected BaseEntityTest(final Class<E> entityClass) {
        super(entityClass);
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @DisplayName("toString() should return non-blank")
    @TestFactory
    DynamicTest _NotBlank_ToString() {
        return _MappedEntityDynamicTests.toString_NonBlank_(entityClass, this::newEntityInstance);
    }

//    @Override
//    protected SingleTypeEqualsVerifierApi<E> equalsVerifier(SingleTypeEqualsVerifierApi<E> equalsVerifier) {
//        return super.equalsVerifier(equalsVerifier)
//                .withRedefinedSubclass(entityClass)
//                ;
//    }

    // ------------------------------------------------------------------------------------------------------- accessors
    @DisplayName("accessors should return normally")
    @TestFactory
    Stream<DynamicTest> _shouldReturn_Accessors() throws IntrospectionException {
        return _MappedEntityDynamicTests.accessors_DoesNothrow_(
                entityClass,
                this::newEntityInstance,
                (d, m) -> _MappedEntity.class.isAssignableFrom(m.getDeclaringClass())
        );
    }
}
