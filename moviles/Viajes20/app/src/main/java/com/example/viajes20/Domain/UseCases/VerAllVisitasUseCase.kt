package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas

class VerAllVisitasUseCase {
    operator fun invoke() = RepositorioVisitas.getAllVisitas()
}