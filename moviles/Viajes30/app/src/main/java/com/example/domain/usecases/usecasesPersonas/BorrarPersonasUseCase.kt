package com.example.domain.usecases.usecasesPersonas

import com.example.data.RepositorioPersonas
import com.example.domain.model.Persona
import javax.inject.Inject

class BorrarPersonasUseCase @Inject constructor(private val repo: RepositorioPersonas) {
    operator fun invoke(persona: Persona): Boolean {
        return repo.delete(persona)
    }
}
