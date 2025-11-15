package com.example.ui.pantallaViajes.pantallaListaVisitas

import com.example.domain.model.Visita

data class ListaViajesState (
    val viajes: List<Visita> = emptyList()
)