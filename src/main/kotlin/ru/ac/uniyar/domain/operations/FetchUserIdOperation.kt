package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import java.util.UUID

fun interface FetchUserIdOperation {
    fun fetch(token: String): Employee?
}

class FetchUserIdOperationImplementation(
    private val employeeRepository: EmployeeRepository
) : FetchUserIdOperation {
    override fun fetch(token: String): Employee? {
        val id = try {
            UUID.fromString(token)
        } catch (_: IllegalArgumentException) {
            return null
        }
        return employeeRepository.fetch(id)
    }
}