package dev.akozel.cleaningtime.core.common.security;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BCryptEncoderTest {

    private BCryptEncoder sut = new BCryptEncoder();

    @Test
    public void should_encrypt_given_string() {
        //given
        String raw = "Hello world";

        //when
        String actual = sut.encode(raw);

        //then
        assertThat(actual)
                .isNotEqualTo(raw);
    }

    @Test
    public void should_compare_raw_and_encrypted_password_as_the_same() {
        //given
        String raw = "Hello world";

        //when
        String encrypted = sut.encode(raw);
        boolean actual = sut.matches(raw, encrypted);

        //then
        assertThat(actual)
                .isTrue();
    }

    @Test
    public void should_compare_different_raw_and_encrypted_password_as_the_different() {
        //given
        String raw = "Hello world";
        String differentRaw = "Hello world1";

        //when
        String encrypted = sut.encode(raw);
        boolean actual = sut.matches(differentRaw, encrypted);

        //then
        assertThat(actual)
                .isFalse();
    }

}
