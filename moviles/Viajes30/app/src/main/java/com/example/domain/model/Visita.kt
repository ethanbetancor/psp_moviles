package com.example.domain.model

data class Visita(
    val id: Int = 0,
    val ciudad: String = "",
    val pais: String= "",
    val fecha: String= "",
    val motivo: String= "",
    val participantes: List<String> = emptyList(),
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