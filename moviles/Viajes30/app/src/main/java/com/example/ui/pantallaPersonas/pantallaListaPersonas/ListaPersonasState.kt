package com.example.ui.pantallaPersonas.pantallaListaPersonas

import com.example.domain.model.Persona

data class ListaPersonasState (
    val personas: List<Persona> = emptyList()
)