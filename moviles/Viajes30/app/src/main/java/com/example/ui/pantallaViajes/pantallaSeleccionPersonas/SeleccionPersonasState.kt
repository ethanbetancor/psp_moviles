package com.example.ui.pantallaViajes.pantallaSeleccionPersonas

import com.example.domain.model.Persona

data class SeleccionPersonasState (
    val PersonasViaje: List<Persona> = emptyList()
)