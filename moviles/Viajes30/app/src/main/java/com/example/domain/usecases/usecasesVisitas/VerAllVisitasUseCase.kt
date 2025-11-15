package com.example.domain.usecases.usecasesVisitas

import com.example.data.RepositorioVisitas
import javax.inject.Inject

class VerAllVisitasUseCase @Inject constructor(private val repo: RepositorioVisitas) {
    operator fun invoke() = repo.getAllVisitas()
}