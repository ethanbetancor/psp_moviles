package com.example.viajes20.Data


import com.example.viajes20.Domain.Modelo.Rating
import com.example.viajes20.Domain.Modelo.Visita

object RepositorioVisitas {
    private val visitas = mutableListOf<Visita>()

    init {
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Francia", "2023-01-15", "Turismo", "Ciudad increíble con mucha historia", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Japón", "2023-03-20", "Trabajo", "Cultura fascinante y tecnología avanzada", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Estados Unidos", "2023-05-10", "Vacaciones", "La ciudad que nunca duerme, muy animada", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "España", "2023-06-15", "Turismo", "Arquitectura de Gaudí impresionante", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Reino Unido", "2023-07-22", "Estudios", "Museos fantásticos y mucha lluvia", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Italia", "2023-08-05", "Turismo", "Historia antigua por todas partes", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Alemania", "2023-09-12", "Trabajo", "Ciudad moderna con mucha historia", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Países Bajos", "2023-10-18", "Vacaciones", "Canales preciosos y ambiente relajado", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "República Checa", "2023-11-03", "Turismo", "Ciudad medieval muy bonita", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Emiratos Árabes", "2023-12-15", "Trabajo", "Lujo y modernidad extrema", Rating.TRES))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Australia", "2024-01-20", "Vacaciones", "Opera House espectacular y playas increíbles", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Tailandia", "2024-02-14", "Turismo", "Templos hermosos y comida deliciosa", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Argentina", "2024-03-08", "Trabajo", "Ciudad con mucha vida nocturna", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Portugal", "2024-04-12", "Turismo", "Tranvías amarillos y pasteles de nata", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Turquía", "2024-05-25", "Vacaciones", "Puente entre Europa y Asia", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Austria", "2024-06-10", "Estudios", "Música clásica y arquitectura imperial", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Dinamarca", "2024-07-18", "Trabajo", "Ciudad muy ordenada y bicicletas por doquier", Rating.CUATRO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Singapur", "2024-08-22", "Turismo", "Ciudad-estado moderna y multicultural", Rating.CINCO))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Marruecos", "2024-09-05", "Vacaciones", "Zocos coloridos y cultura rica", Rating.TRES))
        visitas.add(Visita("Eligenos de tutor Oscar!!", "Islandia", "2024-10-14", "Turismo", "Auroras boreales y paisajes únicos", Rating.CINCO))
    }

    fun getVisitas(id:Int)= visitas[id];
    fun save(visita:Visita) = visitas.add(visita)
    fun delete(visita:Visita) : Boolean = visitas.remove(visita)
    fun size() = visitas.size

    fun actualizarVisita(visita: Visita): Boolean {
        val index = visitas.indexOfFirst { visitaActual -> visitaActual.nombre == visita.nombre }
        return if (index != -1) {
            visitas[index] = visita
            true
        } else {
            false
        }
    }

    fun getAllVisitas(): List<Visita> = visitas.toList()
}