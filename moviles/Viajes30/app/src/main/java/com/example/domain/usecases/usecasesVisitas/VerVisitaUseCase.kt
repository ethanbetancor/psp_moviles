package com.example.domain.usecases.usecasesVisitas

import com.example.data.RepositorioVisitas
import com.example.domain.model.Visita
import javax.inject.Inject

class VerVisitaUseCase @Inject constructor(private val repo: RepositorioVisitas) {
    operator fun invoke(id: Int): Visita = repo.getVisita(id)
}