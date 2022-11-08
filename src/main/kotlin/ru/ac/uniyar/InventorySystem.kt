package ru.ac.uniyar

import org.http4k.core.ContentType
import org.http4k.core.HttpHandler
import org.http4k.core.RequestContexts
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.lens.RequestContextKey
import org.http4k.lens.RequestContextLens
import org.http4k.routing.ResourceLoader
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Undertow
import org.http4k.server.asServer
import ru.ac.uniyar.domain.operations.OperationStorage
import ru.ac.uniyar.domain.operations.computations.SaltSetting
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.domain.storage.initializeRepositoryStorage
import ru.ac.uniyar.web.filters.AuthenticationFilter
import ru.ac.uniyar.web.filters.AuthorizationFilter
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.filters.ShowErrorMessageFilter
import ru.ac.uniyar.web.formRouter
import ru.ac.uniyar.web.handlers.HandlerStorage
import ru.ac.uniyar.web.templates.ContextAwarePebbleTemplates
import ru.ac.uniyar.web.templates.ContextAwareViewRender

fun main() {
    val storage = initializeRepositoryStorage()
    val operationStorage = OperationStorage(storage)

    val renderer = ContextAwarePebbleTemplates().HotReload("src/main/resources")
    val htmlView = ContextAwareViewRender.withContentType(renderer, ContentType.TEXT_HTML)

    val contexts = RequestContexts()
    val currentEmployeeLens: RequestContextLens<Employee?> = RequestContextKey.optional(
        store = contexts,
        name = "employee"
    )
    val permissionsLens: RequestContextLens<RolePermissions> = RequestContextKey.required(
        store = contexts,
        name = "permissions"
    )
    htmlView.associateContextLens("currentEmployee", currentEmployeeLens)
    htmlView.associateContextLens("permissions", permissionsLens)

    val jwtTools = JwtTools(SaltSetting().salt, "ru.ac.uniyar.InventorySystem")

    val handlerStorage = HandlerStorage(
        operationStorage,
        htmlView,
        jwtTools,
        permissionsLens
    )

    val dynamicRouter = formRouter(handlerStorage)
    val staticFilesHandler = static(ResourceLoader.Classpath("/ru/ac/uniyar/public"))
    val routes = routes(
        dynamicRouter,
        staticFilesHandler
    )

    val authorizationApp = AuthenticationFilter(
        currentEmployeeLens,
        operationStorage.fetchUserIdOperation,
        jwtTools
    ).then(
        AuthorizationFilter(
            currentEmployeeLens,
            permissionsLens,
            operationStorage.fetchPermissionOperation
        ).then(routes)
    )

    val app = routes(
        authorizationApp,
        staticFilesHandler
    )

    val finalApp: HttpHandler = ServerFilters.InitialiseRequestContext(contexts)
        .then(ShowErrorMessageFilter(htmlView))
        .then(app)

    val server = finalApp.asServer(Undertow(9000)).start()
    println("Server started on http://localhost:" + server.port())
}
