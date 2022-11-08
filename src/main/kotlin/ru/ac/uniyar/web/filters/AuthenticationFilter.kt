package ru.ac.uniyar.web.filters

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.cookie.cookie
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.FetchUserIdOperation
import ru.ac.uniyar.domain.storage.Employee

class AuthenticationFilter(
    private val currentEmployee: RequestContextLens<Employee?>,
    private val fetchEmployeeViaUserId: FetchUserIdOperation,
    private val jwtTools: JwtTools
) : Filter {
    override fun invoke(next: HttpHandler): HttpHandler = { request: Request ->
        val requestWithEmployee = request.cookie("token")?.value?.let { token ->
            jwtTools.subject(token)
        }?.let { userId ->
            fetchEmployeeViaUserId.fetch(userId)
        }?.let { employee ->
            request.with(currentEmployee of employee)
        } ?: request
        next(requestWithEmployee)
    }
}
