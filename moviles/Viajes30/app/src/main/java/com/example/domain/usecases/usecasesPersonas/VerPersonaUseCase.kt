package com.example.domain.usecases.usecasesPersonas

import com.example.data.RepositorioPersonas
import com.example.domain.model.Persona
import javax.inject.Inject

class VerPersonaUseCase @Inject constructor(private val repo: RepositorioPersonas) {
    operator fun invoke(id: Int): Persona = repo.getPersona(id)
}