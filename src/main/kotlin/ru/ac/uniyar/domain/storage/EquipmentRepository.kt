package ru.ac.uniyar.domain.storage

import java.util.UUID

class EquipmentRepository(equipmentList: List<Equipment>) {
    private val equipment = equipmentList.associateBy { it.id }.toMutableMap()

    fun list(): List<Equipment> = equipment.values.toList()

    fun fetch(id: UUID) = equipment[id]

    fun add(newEquipment: Equipment): UUID {
        var newId = newEquipment.id
        while (equipment.containsKey(newId) || newId == UUID(0, 0)) {
            newId = UUID.randomUUID()
        }
        equipment[newId] = newEquipment.setId(newId)
        return newId
    }

    fun edit(editEquipment: Equipment) {
        equipment[editEquipment.id] = editEquipment
    }
}
