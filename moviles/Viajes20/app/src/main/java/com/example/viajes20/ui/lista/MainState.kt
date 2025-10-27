package com.example.viajes20.ui.lista

import com.example.viajes20.Domain.Modelo.Visita

data class MainState (
    val viajes: List<Visita> = emptyList()
)