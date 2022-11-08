package ru.ac.uniyar.web.handlers

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.cookie.Cookie
import org.http4k.core.cookie.SameSite
import org.http4k.core.cookie.cookie
import org.http4k.core.cookie.invalidateCookie
import org.http4k.core.with
import org.http4k.lens.FormField
import org.http4k.lens.Invalid
import org.http4k.lens.Validator
import org.http4k.lens.nonEmptyString
import org.http4k.lens.webForm
import ru.ac.uniyar.domain.operations.AuthorizationException
import ru.ac.uniyar.domain.operations.AuthorizationOperation
import ru.ac.uniyar.web.filters.JwtTools
import ru.ac.uniyar.web.models.LoginFormViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowLoginFormHandler(
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    override fun invoke(request: Request): Response = Response(Status.OK)
        .with(htmlView(request) of LoginFormViewModel())
}

class AuthorizationUserHandler(
    private val authorizationOperation: AuthorizationOperation,
    private val jwtTools: JwtTools,
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    companion object {
        private val loginLens = FormField.nonEmptyString().required("login")
        private val passwordLens = FormField.nonEmptyString().required("password")
        val formLens = Body.webForm(
            Validator.Feedback,
            loginLens,
            passwordLens
        ).toLens()
    }

    override fun invoke(request: Request): Response {
        val webForm = formLens(request)
        if (webForm.errors.isNotEmpty()) return Response(Status.BAD_REQUEST)
            .with(htmlView(request) of LoginFormViewModel(webForm))
        val id = try {
            authorizationOperation.authorization(
                login = loginLens(webForm),
                password = passwordLens(webForm)
            )
        } catch (_: AuthorizationException) {
            val errorsWebForm = webForm.copy(
                errors = webForm.errors + Invalid(
                    passwordLens.meta.copy(description = "Wrong login or password.")
                )
            )
            return Response(Status.BAD_REQUEST)
                .with(htmlView(request) of LoginFormViewModel(errorsWebForm))
        }
        val token = jwtTools.create(id) ?: return Response(Status.INTERNAL_SERVER_ERROR)
        val cookie = Cookie(
            name = "token",
            value = token,
            httpOnly = true,
            sameSite = SameSite.Strict
        )
        return Response(Status.FOUND).header("location", "/").cookie(cookie)
    }
}

class LogOutUserHandler : HttpHandler {
    override fun invoke(p1: Request): Response = Response(Status.FOUND)
        .header("location", "/")
        .invalidateCookie("token")
}
