package com.example.gccoffee.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    @DisplayName("이메일 validation 테스트")
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class,()->{
            var email = new Email("accccc");
        });
    }
    @Test
    @DisplayName("이메일 validation 테스트")
    public void testValidEmail() {
        var email = new Email("sejun@gmail.com");
        assertThat(email.getAddress()).isEqualTo("sejun@gmail.com");
    }
    @Test
    @DisplayName("이메일 equality 테스트")
    public void testEqEmail() {
        var email = new Email("sejun@gmail.com");
        var email2 = new Email("sejun@gmail.com");
        assertThat(email).isEqualTo(email2);
    }
}