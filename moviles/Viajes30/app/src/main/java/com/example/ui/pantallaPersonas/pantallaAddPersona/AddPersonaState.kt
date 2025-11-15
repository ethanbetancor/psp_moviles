package com.example.ui.pantallaPersonas.pantallaAddPersona

import com.example.domain.model.Persona

data class AddPersonaState(
    val persona: Persona = Persona(),
    val error: String? = null,
    val guardado: Boolean = false
)
