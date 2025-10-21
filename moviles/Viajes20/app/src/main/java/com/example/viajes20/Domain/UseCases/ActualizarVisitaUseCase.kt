package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas
import com.example.viajes20.Domain.Modelo.Visita

class ActualizarVisitaUseCase {
    operator fun invoke(vista : Visita) : Boolean {
        return RepositorioVisitas.actualizarVisita(vista)


    }
}