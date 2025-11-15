package com.example.domain.usecases.usecasesVisitas

import com.example.data.RepositorioVisitas
import com.example.domain.model.Visita
import javax.inject.Inject

class BorrarVisitasUseCase @Inject constructor(private val repo: RepositorioVisitas) {
    operator fun invoke(visita : Visita): Boolean {
        return repo.delete(visita)
    }
}