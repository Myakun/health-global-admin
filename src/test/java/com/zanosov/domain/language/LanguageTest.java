package com.zanosov.domain.language;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LanguageTest {
    private final UUID id = UUID.randomUUID();

    private final Instant now = Instant.now();

    @Nested
    class Constructor {
        @Test
        void shouldThrowWhenCodeIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new Language(null, now, id, new LanguageName("test name"), 0, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("code must not be null");
        }

        @Test
        void shouldThrowWhenNameIsNull() {
            //noinspection DataFlowIssue
            assertThatThrownBy(() -> new Language(new LanguageCode("co"), now, id, null, 0, now))
                    .isInstanceOf(NullPointerException.class)
                    .hasMessage("name must not be null");
        }
    }
}
