package com.wagnermessias.bankapp.core.extension

import java.util.InputMismatchException

private const val CPF_REMAIN_LAST = 11
private val weightCPF = intArrayOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2)

fun String.formatAgency(): String {
    return if (this.length == 9) {
        "${this.substring(0,2)}.${this.substring(2,7)}-${this.substring(7,9)}"
    } else { this }
}

fun String.isEmail(): Boolean {
    return this.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})${"+"}".toRegex())
}

fun String.isCpf(): Boolean {
    if (this.length != CPF_REMAIN_LAST) return false
    return try {
        val digit1 = validateCpfSumByDigit(this.substring(0, 9), weightCPF)
        val digit2 = validateCpfSumByDigit(this.substring(0, 9) + digit1, weightCPF)
        this == this.substring(0, 9) + digit1.toString() + digit2.toString()
    } catch (e: InputMismatchException) { false }
}

private fun validateCpfSumByDigit(str: String, weight: IntArray): Int {
    var sum = 0
    var index = str.length - 1
    var digit: Int
    while (index >= 0) {
        digit = Integer.parseInt(str.substring(index, index + 1))
        sum += digit * weight[weight.size - str.length + index]
        index--
    }
    sum = CPF_REMAIN_LAST - sum % CPF_REMAIN_LAST
    return if (sum > 9) 0 else sum
}