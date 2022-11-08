package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class HandlerStorage(
    operationStorage: OperationStorage,
    htmlView: ContextAwareViewRender,
    jwtTools: JwtTools,
    permissionsLens: RequestContextLens<RolePermissions>
) {
    val showEmployeesHandler: HttpHandler = ShowEmployeesHandler(
        operationStorage.listEmployeesOperation,
        htmlView,
        permissionsLens
    )

    val showEmployeeHandler: HttpHandler = ShowEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        htmlView,
        permissionsLens,
    )

    val showEquipmentHandler: HttpHandler = ShowEquipmentHandler(
        operationStorage.fetchEquipmentOperation,
        htmlView,
        permissionsLens
    )

    val showEquipmentListHandler: HttpHandler = ShowEquipmentListHandler(
        operationStorage.listEquipmentOperation,
        htmlView,
        permissionsLens
    )

    val showStartPageHandler: HttpHandler = ShowStartPageHandler(
        htmlView,
    )

    val inputDataEquipmentHandler: HttpHandler = InputDataEquipmentHandler(
        htmlView,
    )

    val enterDataEquipmentHandler: HttpHandler = EnterDataEquipmentHandler(
        operationStorage.addEquipmentOperation,
        htmlView,
    )

    val inputDataEmployeeHandler: HttpHandler = InputDataEmployeeHandler(
        htmlView,
    )

    val enterDataEmployeeHandler: HttpHandler = EnterDataEmployeeHandler(
        operationStorage.addEmployeeOperation,
        htmlView,
    )

    val editFormEquipmentHandler: HttpHandler = EditFormEquipmentHandler(
        operationStorage.fetchEquipmentOperation,
        htmlView,
    )

    val applyEditEquipmentHandler: HttpHandler = ApplyEditEquipmentHandler(
        operationStorage.editEquipmentOperation,
        operationStorage.fetchEquipmentOperation,
        htmlView,
    )

    val editFormEmployeeHandler: HttpHandler = EditFormEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        htmlView,
    )

    val applyEditEmployeeHandler: HttpHandler = ApplyEditFormEmployeeHandler(
        operationStorage.editEmployeeOperation,
        operationStorage.fetchEmployeeOperation,
        htmlView,
    )

    val showLoginFormHandler: HttpHandler = ShowLoginFormHandler(
        htmlView,
    )

    val authorizationUserHandler: HttpHandler = AuthorizationUserHandler(
        operationStorage.authorizationOperation,
        jwtTools,
        htmlView,
    )

    val logOutUserHandler: HttpHandler = LogOutUserHandler()
}
