package com.example.primer_proyecto.domain.usecases

import com.example.primer_proyecto.data.RepositorioVisitas

class SizeVisitasUseCase {
    operator fun invoke(): Int {
        return RepositorioVisitas.size()
    }
}