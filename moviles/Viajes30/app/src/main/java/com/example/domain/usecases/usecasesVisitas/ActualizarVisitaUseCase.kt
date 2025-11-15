package com.example.domain.usecases.usecasesVisitas

import com.example.data.RepositorioVisitas
import com.example.domain.model.Visita
import javax.inject.Inject

class ActualizarVisitaUseCase @Inject constructor(private val repo: RepositorioVisitas) {
    operator fun invoke(vista : Visita) : Boolean {
        return repo.actualizarVisita(vista)
    }
}