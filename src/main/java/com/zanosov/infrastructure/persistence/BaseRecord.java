package com.zanosov.infrastructure.persistence;

import com.fasterxml.uuid.Generators;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.Hibernate;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseRecord<T extends BaseRecord<T>> {

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id = Generators.timeBasedEpochGenerator().generate();

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @SuppressWarnings("unchecked")
    public T setCreatedAt(@NonNull Instant createdAt) {
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setId(@NonNull UUID id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setUpdatedAt(@NonNull Instant updatedAt) {
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
        return (T) this;
    }

    @PrePersist
    protected void prePersist() {
        var now = Instant.now();

        if (createdAt == null) {
            createdAt = now;
        }

        if (updatedAt == null) {
            updatedAt = now;
        }
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = Instant.now();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseRecord<?> that)) {
            return false;
        }

        if (!Hibernate.getClass(this).equals(Hibernate.getClass(obj))) {
            return false;
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode(); // id never null — generated at field init
    }
}
