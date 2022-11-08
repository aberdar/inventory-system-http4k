package ru.ac.uniyar.web.handlers

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.lens.FormField
import org.http4k.lens.Path
import org.http4k.lens.Validator
import org.http4k.lens.localDate
import org.http4k.lens.nonEmptyString
import org.http4k.lens.string
import org.http4k.lens.uuid
import org.http4k.lens.webForm
import ru.ac.uniyar.domain.operations.EditEquipmentOperation
import ru.ac.uniyar.domain.operations.FetchEquipmentOperation
import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.web.models.EditFormEquipmentViewModel
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class EditFormEquipmentHandler(
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    companion object {
        private val idLens = Path.uuid().of("id")
    }

    override fun invoke(request: Request): Response {
        val equipmentToEdit = fetchEquipmentOperation.fetch(idLens.invoke(request)) ?: return Response(Status.BAD_REQUEST)
        return Response(Status.OK).with(
            htmlView(request) of EditFormEquipmentViewModel(
                equipmentToEdit.name,
                equipmentToEdit.productId,
                equipmentToEdit.description,
                equipmentToEdit.submissionDate
            )
        )
    }
}

class ApplyEditEquipmentHandler(
    private val editEquipmentOperation: EditEquipmentOperation,
    private val fetchEquipmentOperation: FetchEquipmentOperation,
    private val htmlView: ContextAwareViewRender
) : HttpHandler {
    companion object {
        private val idLens = Path.uuid().of("id")
        private val nameLens = FormField.nonEmptyString().required("name")
        private val productIdLens = FormField.nonEmptyString().required("productId")
        private val descriptionLens = FormField.string().required("description")
        private val submissionDateLens = FormField.localDate().required("submissionDate")
        val formLens = Body.webForm(
            Validator.Feedback,
            nameLens,
            productIdLens,
            descriptionLens,
            submissionDateLens
        ).toLens()
    }

    override fun invoke(request: Request): Response {
        val equipmentToEdit = fetchEquipmentOperation.fetch(idLens.invoke(request)) ?: return Response(Status.BAD_REQUEST)
        val webForm = formLens(request)
        if (webForm.errors.isEmpty()) {
            editEquipmentOperation.edit(
                Equipment(
                    equipmentToEdit.id,
                    nameLens(webForm),
                    productIdLens(webForm),
                    descriptionLens(webForm),
                    submissionDateLens(webForm)
                )
            )
            return Response(Status.FOUND).header("location", "/equipment/${equipmentToEdit.id}")
        }
        return Response(Status.BAD_REQUEST).with(
            htmlView(request) of EditFormEquipmentViewModel(
                webForm = webForm
            )
        )
    }
}
