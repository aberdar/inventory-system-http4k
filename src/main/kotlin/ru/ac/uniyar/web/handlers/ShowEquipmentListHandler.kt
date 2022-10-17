package ru.ac.uniyar.web.handlers

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.lens.RequestContextLens
import ru.ac.uniyar.domain.operations.ListEquipmentOperation
import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.web.models.ShowEquipmentVM
import ru.ac.uniyar.web.templates.ContextAwareViewRender

class ShowEquipmentListHandler(
    private val listEquipmentOperation: ListEquipmentOperation,
    private val htmlView: ContextAwareViewRender,
    private val permissionLens: RequestContextLens<RolePermissions>,
) : HttpHandler {
    override fun invoke(request: Request): Response {
        val permissions = permissionLens(request)
        if (permissions.showEquipment) {
            return Response(Status.OK).with(
                htmlView(request) of ShowEquipmentVM(listEquipmentOperation.list())
            )
        }
        return Response(Status.UNAUTHORIZED)
    }
}
