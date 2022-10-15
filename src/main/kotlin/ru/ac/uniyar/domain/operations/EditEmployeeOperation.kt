package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository

fun interface EditEmployeeOperation {
    fun edit(employee: Employee)
}

class EditEmployeeOperationImplementation(
    private val employeeRepository: EmployeeRepository
) : EditEmployeeOperation {
    override fun edit(employee: Employee) {
        employeeRepository.edit(employee)
    }
}