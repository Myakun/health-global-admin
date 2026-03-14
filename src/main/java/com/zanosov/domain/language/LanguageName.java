package com.zanosov.domain.language;

public record LanguageName(String value) {
    public static final int MIN_LENGTH = 3;

    public static final int MAX_LENGTH = 50;

    public LanguageName {
        if (value == null) {
            throw new IllegalArgumentException("Language name must be not null");
        }

        value = value.strip();

        if (value.length() < MIN_LENGTH || value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Language name must be between %d and %d characters long", MIN_LENGTH, MAX_LENGTH)
            );
        }
    }
}
