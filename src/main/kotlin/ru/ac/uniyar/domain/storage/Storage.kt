package ru.ac.uniyar.domain.storage

import java.time.LocalDate
import java.util.UUID

data class Storage(
    val employeeRepository: EmployeeRepository,
    val equipmentRepository: EquipmentRepository,
    val rolePermissionsRepository: RolePermissionsRepository,
)

fun initializeRepositoryStorage(): Storage {
    val adminRole = RolePermissions(
        id = UUID.fromString("28661a42-7f48-4962-a4ac-31dac0dca5ac"),
        name = "Admin",
        showEmployees = true,
        showEmployee = true,
        showEquipment = false,
        showEquipmentItem = false
    )

    val employeeRole = RolePermissions(
        id = UUID.fromString("c3783767-7876-4f6d-b56c-66e4607ce9ca"),
        name = "Employee",
        showEmployees = false,
        showEmployee = false,
        showEquipment = true,
        showEquipmentItem = true
    )

    val rolePermissionsRepository: RolePermissionsRepository = RolePermissionsRepository(listOf(
        adminRole,
        employeeRole,
    ))

    val petr = Employee(
        id = UUID.fromString("8333f4b1-322e-4baa-be42-05079d9fbb95"),
        roleId = adminRole.id,
        name = "Пётр Васильевич Григорьев",
        login = "petr",
        password = "ca5dd103f5644ce0196ca3d0a80ff22b25965110f005fc6b914f2ed90b9930d323bb89ab5b67590ce7a24d73e847d354ba6dd0f6cb69b4d3e9f2dd8d26d0535c",
        phone = "+790000000001",
    )
    val ivan = Employee(
        id = UUID.fromString("c9096a41-b3c5-485a-8756-fd5056a8a944"),
        roleId = employeeRole.id,
        name = "Иван Сергеевич Ушаков",
        login = "ivan",
        password = "ca5dd103f5644ce0196ca3d0a80ff22b25965110f005fc6b914f2ed90b9930d323bb89ab5b67590ce7a24d73e847d354ba6dd0f6cb69b4d3e9f2dd8d26d0535c",
        phone = "+790000000002",
    )
    val irina = Employee(
        id = UUID.fromString("8d93f93f-d491-45ed-ac94-66ad04a01e00"),
        roleId = employeeRole.id,
        name = "Ирина Николаевна Кук",
        login = "irina",
        password = "ca5dd103f5644ce0196ca3d0a80ff22b25965110f005fc6b914f2ed90b9930d323bb89ab5b67590ce7a24d73e847d354ba6dd0f6cb69b4d3e9f2dd8d26d0535c",
        phone = "+790000000003",
    )
    val employeeRepository = EmployeeRepository(listOf(
        petr,
        ivan,
        irina,
    ))

    val printer = Equipment(
        id = UUID.fromString("8ab75a8b-c39c-4607-b253-5615e6e5e4db"),
        name = "Принтер HP LazerJet 1000",
        productId = "578-575-755-555",
        description = "Старый проверенный принтер",
        submissionDate = LocalDate.of(2010, 10, 15),
    )
    val phone = Equipment(
        id = UUID.fromString("2b985f47-dfcc-45a1-b41c-f4f01f8b2b6f"),
        name = "Samsung Galaxy S4",
        productId = "8845-3155-6655",
        description = "Мощный флагман",
        submissionDate = LocalDate.of(2015, 7, 6),
    )
    val equipmentRepository = EquipmentRepository(listOf(
        printer,
        phone,
    ))
    return Storage(
        employeeRepository = employeeRepository,
        equipmentRepository = equipmentRepository,
        rolePermissionsRepository = rolePermissionsRepository,
    )
}