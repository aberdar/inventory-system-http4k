package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.operations.computations.SaltSetting
import ru.ac.uniyar.domain.operations.computations.hashPassword
import ru.ac.uniyar.domain.storage.EmployeeRepository

fun interface AuthorizationOperation {
    fun authorization(
        login: String,
        password: String
    ): String
}

class AuthorizationOperationImplementation(
    private val employeeRepository: EmployeeRepository
) : AuthorizationOperation {
    override fun authorization(
        login: String,
        password: String
    ): String {
        val employee = employeeRepository.list().find { it.login == login } ?: throw AuthorizationException()
        val hashingPassword = hashPassword(password, SaltSetting().salt)
        if (hashingPassword != employee.password) throw AuthorizationException()
        return employee.id.toString()
    }
}

class AuthorizationException : RuntimeException("Wrong login or password.")