package com.zanosov.domain.language;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LanguageNameTest {

    @Nested
    class Constructor {

        @Test
        void shouldThrowWhenValueIsNull() {
            assertThatThrownBy(() -> new LanguageName(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language name must be not null");
        }

        @Test
        void shouldThrowWhenValueIsTooShort() {
            assertThatThrownBy(() -> new LanguageName("ab"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language name must be between 3 and 50 characters long");
        }

        @Test
        void shouldThrowWhenValueIsTooLong() {
            assertThatThrownBy(() -> new LanguageName("a".repeat(51)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language name must be between 3 and 50 characters long");
        }

        @Test
        void shouldTrimWhitespace() {
            assertThat(new LanguageName("  English  ").value()).isEqualTo("English");
        }

        @Test
        void shouldThrowWhenValueIsTooShortAfterTrim() {
            assertThatThrownBy(() -> new LanguageName("  ab  "))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language name must be between 3 and 50 characters long");
        }

        @Test
        void shouldAcceptValueAtMinLength() {
            assertThat(new LanguageName("abc").value()).isEqualTo("abc");
        }

        @Test
        void shouldAcceptValueAtMaxLength() {
            var name = "a".repeat(50);
            assertThat(new LanguageName(name).value()).isEqualTo(name);
        }
    }
}
