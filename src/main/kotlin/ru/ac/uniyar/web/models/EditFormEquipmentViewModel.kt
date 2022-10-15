package ru.ac.uniyar.web.models

import org.http4k.lens.WebForm
import org.http4k.template.ViewModel
import java.time.LocalDate

data class EditFormEquipmentViewModel(
    val name: String? = null,
    val productId: String? = null,
    val description: String? = null,
    val submissionDate: LocalDate? = null,
    val webForm: WebForm = WebForm(),
) : ViewModel
