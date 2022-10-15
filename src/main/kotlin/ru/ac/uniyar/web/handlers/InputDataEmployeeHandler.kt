package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.FormField
import org.http4k.lens.Validator
import org.http4k.lens.nonEmptyString
import org.http4k.lens.webForm
import ru.ac.uniyar.domain.operations.AddEmployeeOperation
import ru.ac.uniyar.web.models.CreateEmployeeViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class InputDataEmployeeHandler(
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(htmlView(request) of CreateEmployeeViewModel())
    }
}

class EnterDataEmployeeHandler(
    private val addEmployeeOperation: AddEmployeeOperation,
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    companion object {
        private val nameLens = FormField.nonEmptyString().required("name")
        private val loginLens = FormField.nonEmptyString().required("login")
        private val phoneLens = FormField.nonEmptyString().required("phone")
        val formLens = Body.webForm(
            Validator.Feedback,
            nameLens,
            loginLens,
            phoneLens
        ).toLens()
    }

    override fun invoke(request: Request): Response {
        val webForm = formLens(request)
        if (webForm.errors.isEmpty()) {
            addEmployeeOperation.add(
                nameLens(webForm),
                loginLens(webForm),
                phoneLens(webForm)
            )
            return Response(Status.FOUND).header("location", "/employees")
        }
        return Response(Status.BAD_REQUEST).with(htmlView(request) of CreateEmployeeViewModel(webForm))
    }
}