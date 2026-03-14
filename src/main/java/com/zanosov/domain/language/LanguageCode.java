package com.zanosov.domain.language;

public record LanguageCode(String value) {
    public static final int LENGTH = 2;

    public LanguageCode {
        if (value == null) {
            throw new IllegalArgumentException("Language code must be not null");
        }

        value = value.strip().toLowerCase();

        if (value.length() != LENGTH) {
            throw new IllegalArgumentException(
                    String.format("Language code must be %d characters long", LENGTH)
            );
        }
    }
}
