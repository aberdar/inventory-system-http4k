package ru.ac.uniyar.domain.operations.computations

fun hashPassword(password: String, salt: String): String {
    val saltedPassword = password + salt
    return CheckSum(saltedPassword.toByteArray()).toString()
}

//test - ca5dd103f5644ce0196ca3d0a80ff22b25965110f005fc6b914f2ed90b9930d323bb89ab5b67590ce7a24d73e847d354ba6dd0f6cb69b4d3e9f2dd8d26d0535c