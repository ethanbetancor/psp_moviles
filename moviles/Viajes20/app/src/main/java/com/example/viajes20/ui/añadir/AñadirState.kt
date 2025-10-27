package com.example.viajes20.ui.añadir

import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.ui.commons.UiEvent

data class AñadirState(
    val visita: Visita = Visita(),
    val event: UiEvent? = null,
    val error: String? = null
)