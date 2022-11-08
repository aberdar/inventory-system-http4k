package ru.ac.uniyar.domain.operations.computations

import io.github.cdimascio.dotenv.dotenv

class SaltSetting {
    companion object {
        private val dotenv = dotenv()
    }

    val salt: String
        get() = dotenv["SALT"]
}
