package com.example.domain.usecases.usecasesVisitas

import com.example.data.RepositorioVisitas
import javax.inject.Inject

class SizeVisitasUseCase @Inject constructor(private val repo: RepositorioVisitas) {
    operator fun invoke(): Int {
        return repo.size()
    }
}