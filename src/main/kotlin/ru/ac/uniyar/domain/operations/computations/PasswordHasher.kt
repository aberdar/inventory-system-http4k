package ru.ac.uniyar.domain.operations.computations

fun hashPassword(password: String, salt: String): String {
    val saltedPassword = password + salt
    return CheckSum(saltedPassword.toByteArray()).toString()
}
