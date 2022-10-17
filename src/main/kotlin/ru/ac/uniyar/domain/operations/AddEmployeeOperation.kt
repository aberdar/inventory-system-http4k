package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import java.util.UUID

interface AddEmployeeOperation {
    fun add(
        name: String,
        login: String,
        phone: String,
    ): UUID
}

class AddEmployeeOperationImplementation(
    private val employeeRepository: EmployeeRepository
) : AddEmployeeOperation {
    override fun add(
        name: String,
        login: String,
        phone: String,
    ): UUID {
        return employeeRepository.add(
            Employee(
                id = UUID(0,0),
                roleId = UUID.fromString("c3783767-7876-4f6d-b56c-66e4607ce9ca"),
                name = name,
                login = login,
                password = "12345",
                phone = phone
            )
        )
    }
}

