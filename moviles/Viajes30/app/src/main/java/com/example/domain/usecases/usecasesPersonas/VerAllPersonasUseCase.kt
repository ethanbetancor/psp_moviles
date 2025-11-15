package com.example.domain.usecases.usecasesPersonas

import com.example.data.RepositorioPersonas
import javax.inject.Inject

class VerAllPersonasUseCase @Inject constructor(private val repo: RepositorioPersonas) {
    operator fun invoke() = repo.getAllPersonas()
}
