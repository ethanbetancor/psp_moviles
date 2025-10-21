package com.example.viajes20.Data


import com.example.viajes20.Domain.Modelo.Rating
import com.example.viajes20.Domain.Modelo.Visita

object RepositorioVisitas {
    private val visitas = mutableListOf<Visita>()

    init {
        visitas.add(Visita("Juan Perez", "Argentina", "2023-01-15", "Turismo", "Hermoso lugar", Rating.CINCO))
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
}