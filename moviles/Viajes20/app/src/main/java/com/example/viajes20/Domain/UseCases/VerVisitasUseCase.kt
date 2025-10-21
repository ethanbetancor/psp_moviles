package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas
import com.example.viajes20.Domain.Modelo.Visita

class VerVisitasUseCase {
    operator fun invoke(id: Int): Visita = RepositorioVisitas.getVisitas(id)
}