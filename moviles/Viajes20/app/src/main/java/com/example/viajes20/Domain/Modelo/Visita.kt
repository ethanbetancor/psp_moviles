package com.example.viajes20.Domain.Modelo


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