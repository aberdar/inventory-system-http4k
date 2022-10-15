package ru.ac.uniyar.web.models

import org.http4k.lens.WebForm
import org.http4k.template.ViewModel

data class EditFormEmployeeViewModel(
    val name: String? = null,
    val login: String? = null,
    val phone: String? = null,
    val webForm: WebForm = WebForm(),
) : ViewModel
