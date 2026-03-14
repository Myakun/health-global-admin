package com.zanosov.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BaseEntityTest {
    private final UUID aId = UUID.randomUUID();

    private final UUID bId = UUID.randomUUID();

    private final Instant now = Instant.now();

    static class TestEntity extends BaseEntity {
        public TestEntity(Instant createdAt, UUID id, Instant updatedAt) {
            super(createdAt, id, updatedAt);
        }
    }

    static class OtherTestEntity extends BaseEntity {
        public OtherTestEntity(Instant createdAt, UUID id, Instant updatedAt) {
            super(createdAt, id, updatedAt);
        }
    }

    @Nested
    class Constructor {
        @Test
        void shouldThrowWhenIdIsNull() {
            assertThatThrownBy(() -> new TestEntity(now, null, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("id must not be null");
        }

        @Test
        void shouldThrowWhenCreatedAtIsNull() {
            assertThatThrownBy(() -> new TestEntity(null, aId, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("createdAt must not be null");
        }

        @Test
        void shouldThrowWhenUpdatedAtIsNull() {
            assertThatThrownBy(() -> new TestEntity(now, aId, null))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("updatedAt must not be null");
        }
    }

    @Nested
    class Equals {
        @SuppressWarnings("EqualsWithItself")
        @Test
        void shouldReturnTrueForSameReference() {
            var entity = new TestEntity(now, aId, now);

            assertThat(entity).isEqualTo(entity);
        }

        @Test
        void shouldReturnFalseForNull() {
            var entity = new TestEntity(now, aId, now);

            assertThat(entity).isNotEqualTo(null);
        }

        @Test
        void shouldReturnFalseForNonBaseEntityObject() {
            var entity = new TestEntity(now, aId, now);

            assertThat(entity).isNotEqualTo(new Object());
        }

        @SuppressWarnings("AssertBetweenInconvertibleTypes")
        @Test
        void shouldReturnFalseForDifferentClasses() {
            var a = new TestEntity(now, aId, now);
            var b = new OtherTestEntity(now, aId, now);

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void shouldReturnFalseForDifferentIds() {
            var a = new TestEntity(now, aId, now);
            var b = new TestEntity(now, bId, now);

            assertThat(a).isNotEqualTo(b);
        }

        @Test
        void shouldReturnTrueForSameIds() {
            var a = new TestEntity(now, aId, now);
            var b = new TestEntity(now, aId, now);

            assertThat(a).isEqualTo(b);
        }
    }

    @Nested
    class HashCode {
        @Test
        void shouldBeEqualForSameId() {
            var a = new TestEntity(now, aId, now);
            var b = new TestEntity(now, aId, now);

            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }
    }
}
