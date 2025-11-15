package com.example.ui.pantallaViajes.pantallaDetalleVisita

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.model.Visita
import com.example.domain.usecases.usecasesVisitas.VerVisitaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetalleVisitaViewModel @Inject constructor(
    private val verVisitaUseCase: VerVisitaUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _visita = MutableLiveData<Visita>()
    val visita: LiveData<Visita> = _visita

    init {
        // Obtener el ID de la visita de los argumentos de navegación
        val visitaId = savedStateHandle.get<Int>("visitaId") ?: 0
        loadVisita(visitaId)
    }

    private fun loadVisita(id: Int) {
        _visita.value = verVisitaUseCase(id)
    }
}