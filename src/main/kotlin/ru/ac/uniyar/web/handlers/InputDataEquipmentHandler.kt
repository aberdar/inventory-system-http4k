package ru.ac.uniyar.web.handlers

import org.http4k.core.*
import org.http4k.lens.*
import ru.ac.uniyar.domain.operations.AddEquipmentOperation
import ru.ac.uniyar.web.models.CreateEquipmentViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class InputDataEquipmentHandler(
    private val htmlView: ContextAwareViewRender,
) : HttpHandler  {
    override fun invoke(request: Request): Response {
        return Response(Status.OK).with(htmlView(request) of CreateEquipmentViewModel())
    }
}

class EnterDataEquipmentHandler(
    private val addEquipmentOperation: AddEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
) : HttpHandler {
    companion object {
        private val nameLens = FormField.nonEmptyString().required("name")
        private val productId = FormField.nonEmptyString().required("productId")
        private val description = FormField.string().required("description")
        private val submissionDate = FormField.localDate().required("submissionDate")
        val formLens = Body.webForm(
            Validator.Feedback,
            nameLens,
            productId,
            description,
            submissionDate
        ).toLens()
    }

    override fun invoke(request: Request): Response {
        val webForm = formLens(request)
        if (webForm.errors.isEmpty()) {
            addEquipmentOperation.add(
                nameLens(webForm),
                productId(webForm),
                description(webForm),
                submissionDate(webForm)
            )
            return Response(Status.FOUND).header("location", "/equipment")
        }
        return Response(Status.BAD_REQUEST).with(htmlView(request) of CreateEquipmentViewModel(webForm))
    }
}