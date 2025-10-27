package com.example.viajes20.ui.detalle

import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.ui.commons.UiEvent

data class DetalleState (
    val visita : Visita,
    val event : UiEvent? = null
)