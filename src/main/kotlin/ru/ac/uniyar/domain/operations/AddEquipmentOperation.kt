package ru.ac.uniyar.domain.operations

import ru.ac.uniyar.domain.storage.Equipment
import ru.ac.uniyar.domain.storage.EquipmentRepository
import java.time.LocalDate
import java.util.UUID

fun interface AddEquipmentOperation {
    fun add(
        name: String,
        productId: String,
        description: String,
        submissionDate: LocalDate
    ): UUID
}

class AddEquipmentOperationImplementation(
    private val equipmentRepository: EquipmentRepository
) : AddEquipmentOperation {
    override fun add(
        name: String,
        productId: String,
        description: String,
        submissionDate: LocalDate
    ): UUID {
        return equipmentRepository.add(
            Equipment(
                id = UUID(0, 0),
                name = name,
                productId = productId,
                description = description,
                submissionDate = submissionDate
            )
        )
    }
}
