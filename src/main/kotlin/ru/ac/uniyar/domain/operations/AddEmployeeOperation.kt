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
                UUID(0,0),
                name,
                login,
                phone
            )
        )
    }
}

