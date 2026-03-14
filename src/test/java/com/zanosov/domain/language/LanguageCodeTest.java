package com.zanosov.domain.language;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LanguageCodeTest {
    @Nested
    class Constructor {
        @Test
        void shouldThrowWhenValueIsNull() {
            assertThatThrownBy(() -> new LanguageCode(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language code must be not null");
        }

        @Test
        void shouldTrimWhitespace() {
            assertThat(new LanguageCode(" en ").value()).isEqualTo("en");
        }

        @Test
        void shouldConvertToLowercase() {
            assertThat(new LanguageCode("EN").value()).isEqualTo("en");
        }

        @Test
        void shouldTrimAndConvertToLowercase() {
            assertThat(new LanguageCode(" EN ").value()).isEqualTo("en");
        }

        @Test
        void shouldThrowWhenValueLengthIsNotExactly2CharactersLong() {
            assertThatThrownBy(() -> new LanguageCode("test"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Language code must be 2 characters long");
        }
    }
}
