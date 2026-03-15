package com.zanosov.domain.language;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LanguageTranslationNameTest {

    @Nested
    class Constructor {

        @Test
        void shouldThrowWhenValueIsNull() {
            assertThatThrownBy(() -> new LanguageTranslationName(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language translation name must be not null");
        }

        @Test
        void shouldThrowWhenValueIsTooShort() {
            assertThatThrownBy(() -> new LanguageTranslationName("ab"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language translation name must be between 3 and 50 characters long");
        }

        @Test
        void shouldThrowWhenValueIsTooLong() {
            assertThatThrownBy(() -> new LanguageTranslationName("a".repeat(51)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language translation name must be between 3 and 50 characters long");
        }

        @Test
        void shouldTrimWhitespace() {
            assertThat(new LanguageTranslationName("  English  ").value()).isEqualTo("English");
        }

        @Test
        void shouldThrowWhenValueIsTooShortAfterTrim() {
            assertThatThrownBy(() -> new LanguageTranslationName("  ab  "))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language translation name must be between 3 and 50 characters long");
        }

        @Test
        void shouldAcceptValueAtMinLength() {
            assertThat(new LanguageTranslationName("abc").value()).isEqualTo("abc");
        }

        @Test
        void shouldAcceptValueAtMaxLength() {
            var name = "a".repeat(50);
            assertThat(new LanguageTranslationName(name).value()).isEqualTo(name);
        }
    }
}
