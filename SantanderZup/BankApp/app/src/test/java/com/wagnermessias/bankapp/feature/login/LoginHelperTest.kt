package com.wagnermessias.bankapp.feature.login

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginHelperTest {

    private val loginHelper = LoginHelper()
    private lateinit var userEmailValid: String
    private lateinit var passwordValid: String

    @Before
    fun setup() {
        userEmailValid = "messias@gmail.com"
        passwordValid = "Senh@10"
    }

    @Test
    fun userAndPasswordFormatValid_isValidLogin_returnTrue() {
        val expectedIsValidLogin = true

        val result = loginHelper.isValidLogin(userEmailValid, passwordValid)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun userformatNotExpectd_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val user = "blabla"


        val result = loginHelper.isValidLogin(user, passwordValid)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun userformatCPFValid_isValidLogin_returnTrue() {
        val expectedIsValidLogin = true
        val user = "78254083061"

        val result = loginHelper.isValidLogin(user, passwordValid)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun userformatCPFInvalid_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val user = "78254083062"

        val result = loginHelper.isValidLogin(user, passwordValid)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun userformatEmailInvalid_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val user = "messias@gmail.com."

        val result = loginHelper.isValidLogin(user, passwordValid)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun passwordWithoutUppercaseLetter_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val password = "senh@10"

        val result = loginHelper.isValidLogin(userEmailValid, password)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun passwordWithoutSpecialCharacter_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val password = "Senha10"

        val result = loginHelper.isValidLogin(userEmailValid, password)

        assertEquals(expectedIsValidLogin, result)
    }

    @Test
    fun passwordWithoutAlphanumericCharacter_isValidLogin_returnFalse() {
        val expectedIsValidLogin = false
        val password = "@#$="

        val result = loginHelper.isValidLogin(userEmailValid, password)

        assertEquals(expectedIsValidLogin, result)
    }
}
