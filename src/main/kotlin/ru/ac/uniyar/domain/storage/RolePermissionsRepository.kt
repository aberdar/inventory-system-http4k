package ru.ac.uniyar.domain.storage

import java.util.UUID

class RolePermissionsRepository(roleList: List<RolePermissions>) {
    private val rolePermissions = roleList.associateBy { it.id }.toMutableMap()

    fun fetch(id: UUID): RolePermissions? = rolePermissions[id]
}
