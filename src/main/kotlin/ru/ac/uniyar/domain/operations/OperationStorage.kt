package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Storage

class OperationStorage(
    storage: Storage,
) {
    // Employee Operation
    val fetchEmployeeOperation: FetchEmployeeOperation = FetchEmployeeOperationImplementation(
        storage.employeeRepository,
    )
    val listEmployeesOperation: ListEmployeesOperation = ListEmployeesOperationImplementation(
        storage.employeeRepository,
    )
    val addEmployeeOperation: AddEmployeeOperation = AddEmployeeOperationImplementation(
        storage.employeeRepository,
    )
    val editEmployeeOperation: EditEmployeeOperation = EditEmployeeOperationImplementation(
        storage.employeeRepository,
    )

    // Equipment Operation
    val fetchEquipmentOperation: FetchEquipmentOperation = FetchEquipmentOperationImplementation(
        storage.equipmentRepository,
    )
    val listEquipmentOperation: ListEquipmentOperation = ListEquipmentOperationImplementation(
        storage.equipmentRepository,
    )
    val addEquipmentOperation: AddEquipmentOperation = AddEquipmentOperationImplementation(
        storage.equipmentRepository,
    )
    val editEquipmentOperation: EditEquipmentOperation = EditEquipmentOperationImplementation(
        storage.equipmentRepository,
    )

    // Authorization Operation
    val fetchUserIdOperation: FetchUserIdOperation = FetchUserIdOperationImplementation(
        storage.employeeRepository,
    )
    val authorizationOperation: AuthorizationOperation = AuthorizationOperationImplementation(
        storage.employeeRepository,
    )
    val fetchPermissionOperation: FetchPermissionOperation = FetchPermissionOperationImplementation(
        storage.rolePermissionsRepository,
    )
}
