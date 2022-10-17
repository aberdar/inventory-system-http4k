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
    companion object {
        val ANONYMOUS_ROLE = RolePermissions(
            id = UUID.fromString("922fdea9-190a-4f54-b317-98e97457404d"),
            name = "Guest",
            showEmployees = false,
            showEmployee = false,
            showEquipment = false,
            showEquipmentItem = false
        )
    }
}