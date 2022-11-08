package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.FetchPermissionOperation
import ru.ac.uniyar.domain.storage.Employee
import ru.ac.uniyar.domain.storage.RolePermissions

class AuthorizationFilter(
    private val currentEmployee: RequestContextLens<Employee?>,
    private val permissionLens: RequestContextLens<RolePermissions>,
    private val fetchPermissionsOperation: FetchPermissionOperation
) : Filter {
    override fun invoke(next: HttpHandler): HttpHandler = { request: Request ->
        val permissions = currentEmployee(request)?.let {
            fetchPermissionsOperation.fetch(it.roleId)
        } ?: RolePermissions.ANONYMOUS_ROLE
        val authorizedRequest = request.with(permissionLens of permissions)
        next(authorizedRequest)
    }
}
