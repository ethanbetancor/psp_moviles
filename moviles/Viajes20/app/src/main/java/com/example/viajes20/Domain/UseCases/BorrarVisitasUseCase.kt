package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas
import com.example.viajes20.Domain.Modelo.Visita

class BorrarVisitasUseCase {
    operator fun invoke(visita : Visita): Boolean {
        return RepositorioVisitas.delete(visita)

    }
}