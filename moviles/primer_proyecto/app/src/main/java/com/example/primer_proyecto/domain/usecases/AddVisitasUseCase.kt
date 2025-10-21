package com.example.primer_proyecto.domain.usecases

import com.example.primer_proyecto.data.RepositorioVisitas
import com.example.primer_proyecto.domain.modelo.Visita

class AddVisitasUseCase {
    operator fun invoke(visita : Visita): Boolean {
        val guardado: Boolean
        if (RepositorioVisitas.save(visita)) {
            guardado = true
        }else{
            guardado = false
        }
        return guardado
    }
}