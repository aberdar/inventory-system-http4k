package ru.ac.uniyar.web

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import ru.ac.uniyar.web.handlers.HandlerStorage

fun formRouter(
    handlerStorage: HandlerStorage,
) = routes(
    "/" bind Method.GET to handlerStorage.showStartPageHandler,

    "/login" bind Method.GET to handlerStorage.showLoginFormHandler,
    "/login" bind Method.POST to handlerStorage.authorizationUserHandler,
    "/logout" bind Method.GET to handlerStorage.logOutUserHandler,

    "/employees" bind Method.GET to handlerStorage.showEmployeesHandler,
    "/employees/new" bind Method.GET to handlerStorage.inputDataEmployeeHandler,
    "/employees/new" bind Method.POST to handlerStorage.enterDataEmployeeHandler,
    "/employees/{id}" bind Method.GET to handlerStorage.showEmployeeHandler,
    "/employees/{id}/edit" bind Method.GET to handlerStorage.editFormEmployeeHandler,
    "/employees/{id}/edit" bind Method.POST to handlerStorage.applyEditEmployeeHandler,

    "/equipment" bind Method.GET to handlerStorage.showEquipmentListHandler,
    "/equipment/new" bind Method.GET to handlerStorage.inputDataEquipmentHandler,
    "/equipment/new" bind Method.POST to handlerStorage.enterDataEquipmentHandler,
    "/equipment/{id}" bind Method.GET to handlerStorage.showEquipmentHandler,
    "/equipment/{id}/edit" bind Method.GET to handlerStorage.editFormEquipmentHandler,
    "/equipment/{id}/edit" bind Method.POST to handlerStorage.applyEditEquipmentHandler,

    "/ping" bind Method.GET to { Response(Status.OK) },
)
