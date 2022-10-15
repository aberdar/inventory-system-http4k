package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class HandlerStorage(
    operationStorage: OperationStorage,
    htmlView: ContextAwareViewRender,
) {
    val showEmployeesHandler: HttpHandler = ShowEmployeesHandler(
        operationStorage.listEmployeesOperation,
        htmlView,
    )

    val showEmployeeHandler: HttpHandler = ShowEmployeeHandler(
        operationStorage.fetchEmployeeOperation,
        htmlView,
    )

    val showEquipmentHandler: HttpHandler = ShowEquipmentHandler(
        operationStorage.fetchEquipmentOperation,
        htmlView,
    )

    val showEquipmentListHandler: HttpHandler = ShowEquipmentListHandler(
        operationStorage.listEquipmentOperation,
        htmlView,
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

    val editFormEquipmentHandler: HttpHandler =  EditFormEquipmentHandler(
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
        htmlView
    )
}
