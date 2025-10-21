package com.example.primer_proyecto.domain.usecases

import com.example.primer_proyecto.data.RepositorioVisitas
import com.example.primer_proyecto.domain.modelo.Visita

class BorrarVisitasUseCase {
    operator fun invoke(visita : Visita): Boolean {
        return RepositorioVisitas.delete(visita)

    }
}