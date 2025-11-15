package com.example.data


import com.example.domain.model.Rating
import com.example.domain.model.Visita


class RepositorioVisitas {
    private val visitas = mutableListOf<Visita>()

    init {
        visitas.add(Visita(0, "Francia", "Francia", "2023-01-15", "Ciudad increíble con mucha historia", listOf("ethan"), "Visita inolvidable", Rating.CINCO))
        visitas.add(Visita(1, "Viaje a México", "México", "2025-01-10", "Turismo", listOf("Ana", "Luis"), "Playas espectaculares", Rating.CINCO))
        visitas.add(Visita(2, "Viaje a Canadá", "Canadá", "2025-02-15", "Trabajo", listOf("Carlos"), "Ciudades limpias y ordenadas", Rating.CUATRO))
        visitas.add(Visita(3, "Viaje a Brasil", "Brasil", "2025-03-20", "Vacaciones", listOf("María", "Pedro"), "Carnaval inolvidable", Rating.CINCO))
        visitas.add(Visita(4, "Viaje a Rusia", "Rusia", "2025-04-25", "Estudios", listOf("Sofía"), "Arquitectura impresionante", Rating.CUATRO))
        visitas.add(Visita(5, "Viaje a China", "China", "2025-05-30", "Turismo", listOf("Juan", "Lucía"), "Gran Muralla espectacular", Rating.CINCO))
        visitas.add(Visita(6, "Viaje a India", "India", "2025-06-05", "Trabajo", listOf("Miguel"), "Cultura y colores únicos", Rating.TRES))
        visitas.add(Visita(7, "Viaje a Sudáfrica", "Sudáfrica", "2025-07-10", "Vacaciones", listOf("Elena", "Raúl"), "Safari emocionante", Rating.CINCO))
        visitas.add(Visita(8, "Viaje a Grecia", "Grecia", "2025-08-15", "Turismo", listOf("Pablo"), "Ruinas antiguas y mar azul", Rating.CUATRO))
        visitas.add(Visita(9, "Viaje a Noruega", "Noruega", "2025-09-20", "Estudios", listOf("Sara", "David"), "Fiordos impresionantes", Rating.CINCO))
        visitas.add(Visita(10, "Viaje a Egipto", "Egipto", "2025-10-25", "Turismo", listOf("Jorge"), "Pirámides y desierto", Rating.CUATRO))
    }

    fun getVisita(id:Int)= visitas[id];
    fun save(visita:Visita) = visitas.add(visita)
    fun delete(visita:Visita) : Boolean = visitas.remove(visita)
    fun size() = visitas.size

    fun actualizarVisita(visita: Visita): Boolean {
        val index = visitas.indexOfFirst { visitaActual -> visitaActual.ciudad == visita.ciudad }
        return if (index != -1) {
            visitas[index] = visita
            true
        } else {
            false
        }
    }

    fun getAllVisitas(): List<Visita> = visitas.toList()

}