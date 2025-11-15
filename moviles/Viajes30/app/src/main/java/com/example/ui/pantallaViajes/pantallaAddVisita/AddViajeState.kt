package com.example.ui.pantallaViajes.pantallaAddVisita

import com.example.domain.model.Visita

data class AddViajeState(
    val visita: Visita,
    val error: String? = null
)
