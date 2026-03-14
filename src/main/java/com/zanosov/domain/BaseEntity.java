package com.zanosov.domain;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public abstract class BaseEntity {
  private final Instant createdAt;

  private final UUID id;

  private final Instant updatedAt;

  public BaseEntity(@NonNull Instant createdAt, @NonNull UUID id, @NonNull Instant updatedAt) {
    this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    this.id = Objects.requireNonNull(id, "id must not be null");
    this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public UUID getId() {
    return id;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (!(obj instanceof BaseEntity that)) {
      return false;
    }

    if (!getClass().equals(that.getClass())) {
      return false;
    }

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }
}
