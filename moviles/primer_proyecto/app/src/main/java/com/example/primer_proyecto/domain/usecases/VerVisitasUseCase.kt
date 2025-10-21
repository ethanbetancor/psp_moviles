package com.example.primer_proyecto.domain.usecases

import com.example.primer_proyecto.data.RepositorioVisitas
import com.example.primer_proyecto.domain.modelo.Visita

class VerVisitasUseCase {
    operator fun invoke(id: Int): Visita = RepositorioVisitas.getVisitas(id)
}