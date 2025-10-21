package com.example.viajes20.Domain.UseCases

import com.example.viajes20.Data.RepositorioVisitas
import com.example.viajes20.Domain.Modelo.Visita

class AddVisitasUseCase {
    operator fun invoke(visita : Visita): Boolean {
        val guardado: Boolean
        if (RepositorioVisitas.save(visita)) {
            guardado = true
        }else{
            guardado = false
        }
        return guardado
    }
}