package ru.ac.uniyar.domain.storage

import java.util.UUID

data class RolePermissions(
    val id: UUID,
    val name: String,
    val showEmployees: Boolean,
    val showEmployee: Boolean,
    val showEquipment: Boolean,
    val showEquipmentItem: Boolean
) {

}