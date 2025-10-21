package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas

class SizeVisitasUseCase {
    operator fun invoke(): Int {
        return RepositorioVisitas.size()
    }
}