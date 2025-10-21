package com.example.primer_proyecto.domain.modelo


data class Visita (
    val nombre: String = "",
    val pais: String= "",
    val fecha: String= "",
    val motivo: String= "",
    val comentarios: String= "",
    val rating: Rating = Rating.VACIO
)

enum class Rating{
    VACIO,
    UNO,
    DOS,
    TRES,
    CUATRO,
    CINCO
}

