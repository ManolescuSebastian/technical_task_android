package com.tekydevelop.techicaltestsm.utils

import com.google.common.truth.Truth.assertThat

import org.junit.Test

class UtilsTest{

    @Test
    fun `email address has only numbers`(){
        val result = Utils.validateEmailAddress("11111111111")
        assertThat(result).isFalse()
    }

    @Test
    fun `email address has only numbers and @ symbol`(){
        val result = Utils.validateEmailAddress("1111111@mail.com")
        assertThat(result).isTrue()
    }

    @Test
    fun `email address has only one letter for domain name`(){
        val result = Utils.validateEmailAddress("1111111@mail.c")
        assertThat(result).isTrue()
    }

    @Test
    fun `email address is empty`(){
        val result = Utils.validateEmailAddress("")
        assertThat(result).isFalse()
    }

    @Test
    fun `email address is blank`(){
        val result = Utils.validateEmailAddress("         ")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email address`(){
        val result = Utils.validateEmailAddress("myemailaddress")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid email address`(){
        val result = Utils.validateEmailAddress("myemailaddress@email.com")
        assertThat(result).isTrue()
    }
}