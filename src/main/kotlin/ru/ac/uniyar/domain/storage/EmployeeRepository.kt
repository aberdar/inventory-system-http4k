package ru.ac.uniyar.domain.storage

import java.util.UUID

class EmployeeRepository(employees: List<Employee>) {
    private val employees = employees.associateBy { it.id }.toMutableMap()

    fun list() = employees.values.toList()

    fun fetch(id: UUID) = employees[id]

    fun add(employee: Employee): UUID {
        var newId = employee.id
        while (employees.containsKey(newId) || newId == UUID(0,0)) {
            newId = UUID.randomUUID()
        }
        employees[newId] = employee.setId(newId)
        return newId
    }

    fun edit(employee: Employee) {
        employees[employee.id] = employee
    }
}
