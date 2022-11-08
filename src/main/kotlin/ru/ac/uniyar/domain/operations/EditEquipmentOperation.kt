package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository

fun interface EditEquipmentOperation {
    fun edit(equipment: Equipment)
}

class EditEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository
) : EditEquipmentOperation {
    override fun edit(equipment: Equipment) {
        equipmentRepository.edit(equipment)
    }
}
