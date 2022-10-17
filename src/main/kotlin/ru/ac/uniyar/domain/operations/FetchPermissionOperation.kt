package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.RolePermissions
import ru.ac.uniyar.domain.storage.RolePermissionsRepository
import java.util.UUID

fun interface FetchPermissionOperation {
    fun fetch(id: UUID): RolePermissions?
}

class FetchPermissionOperationImplementation(
    private val rolePermissionsRepository: RolePermissionsRepository
) : FetchPermissionOperation {
    override fun fetch(id: UUID): RolePermissions? = rolePermissionsRepository.fetch(id)
}