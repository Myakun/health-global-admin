package com.zanosov.infrastructure.persistence;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseRecordTest {

    private final UUID aId = UUID.randomUUID();

    private final UUID bId = UUID.randomUUID();

    static class TestRecord extends BaseRecord<TestRecord> {
    }

    static class OtherTestRecord extends BaseRecord<OtherTestRecord> {
    }

    @Nested
    class SetCreatedAt {
        @Test
        void shouldThrowWhenCreatedAtIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new TestRecord().setCreatedAt(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("createdAt must not be null");
        }
    }

    @Nested
    class SetId {
        @Test
        void shouldThrowWhenIdIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new TestRecord().setId(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("id must not be null");
        }
    }

    @Nested
    class SetUpdatedAt {
        @Test
        void shouldThrowWhenUpdatedAtIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new TestRecord().setUpdatedAt(null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("updatedAt must not be null");
        }
    }

    @Nested
    class Equals {
        @SuppressWarnings("EqualsWithItself")
        @Test
        void shouldReturnTrueForSameReference() {
            var record = new TestRecord().setId(aId);

            assertThat(record).isEqualTo(record);
        }

        @Test
        void shouldReturnFalseForNull() {
            var record = new TestRecord().setId(aId);

            assertThat(record).isNotEqualTo(null);
        }

        @Test
        void shouldReturnFalseForNonBaseRecordObject() {
            var record = new TestRecord().setId(aId);

            assertThat(record).isNotEqualTo(new Object());
        }

        @SuppressWarnings("AssertBetweenInconvertibleTypes")
        @Test
        void shouldReturnFalseForDifferentClasses() {
            var a = new TestRecord().setId(aId);
            var b = new OtherTestRecord().setId(aId);

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void shouldReturnFalseForDifferentIds() {
            var a = new TestRecord().setId(aId);
            var b = new TestRecord().setId(bId);

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void shouldReturnTrueForSameId() {
            var a = new TestRecord().setId(aId);
            var b = new TestRecord().setId(aId);

            assertThat(a).isEqualTo(b);
        }
    }

    @Nested
    class HashCode {
        @Test
        void shouldBeEqualForSameId() {
            var a = new TestRecord().setId(aId);
            var b = new TestRecord().setId(aId);

            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }
    }

    @Nested
    class PrePersist {

        @Test
        void shouldSetCreatedAtAndUpdatedAtWhenBothNull() {
            var record = new TestRecord();
            record.prePersist();

            assertThat(record.getCreatedAt()).isNotNull();
            assertThat(record.getUpdatedAt()).isNotNull();
        }

        @Test
        void shouldNotOverwriteCreatedAtWhenAlreadySet() {
            var createdAt = Instant.parse("2020-01-01T00:00:00Z");
            var record = new TestRecord().setCreatedAt(createdAt);
            record.prePersist();

            assertThat(record.getCreatedAt()).isEqualTo(createdAt);
        }

        @Test
        void shouldNotOverwriteUpdatedAtWhenAlreadySet() {
            var updatedAt = Instant.parse("2020-01-01T00:00:00Z");
            var record = new TestRecord().setUpdatedAt(updatedAt);
            record.prePersist();

            assertThat(record.getUpdatedAt()).isEqualTo(updatedAt);
        }
    }

    @Nested
    class PreUpdate {

        @Test
        void shouldUpdateUpdatedAt() {
            var oldUpdatedAt = Instant.parse("2020-01-01T00:00:00Z");
            var record = new TestRecord().setUpdatedAt(oldUpdatedAt);
            record.preUpdate();

            assertThat(record.getUpdatedAt()).isAfter(oldUpdatedAt);
        }
    }
}
