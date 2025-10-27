package com.example.primer_proyecto.domain.usecases
import com.example.primer_proyecto.data.RepositorioVisitas
import com.example.primer_proyecto.domain.modelo.Visita

class ActualizarVisitaUseCase {
    operator fun invoke(vista : Visita) : Boolean {
        return RepositorioVisitas.actualizarVisita(vista)
    }
}