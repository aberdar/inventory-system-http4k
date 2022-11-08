package ru.ac.uniyar.domain.operations

import io.github.cdimascio.dotenv.dotenv
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.EmployeeRepository
import java.util.UUID

fun interface AddEmployeeOperation {
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
        val dotenv = dotenv()
        return employeeRepository.add(
            Employee(
                id = UUID(0, 0),
                roleId = UUID.fromString(dotenv["EMPLOYEE_ROLE_ID"]),
                name = name,
                login = login,
                password = dotenv["TEST_PASSWORD"],
                phone = phone
            )
        )
    }
}
