package com.example.primer_proyecto.ui.pantallamain

import com.example.primer_proyecto.domain.modelo.Visita

data class MainState (
    val visita: Visita= Visita(),
    val limpiar: Boolean = false,
    val actualizar: Boolean = false,
    val borrar: Boolean = false,
    val guardar: Boolean = false,
    val anterior: Boolean = false,
    val siguiente: Boolean = false,
    val posicion: Int = 0,
    val total: Int = 0,
    val error: String? = null

)




