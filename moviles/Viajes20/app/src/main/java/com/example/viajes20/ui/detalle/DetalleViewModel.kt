package com.example.viajes20.ui.detalle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.Domain.UseCases.ActualizarVisitaUseCase
import com.example.viajes20.Domain.UseCases.BorrarVisitasUseCase
import com.example.viajes20.Domain.UseCases.VerAllVisitasUseCase
import com.example.viajes20.ui.commons.UiEvent

class DetalleViewModel (
    private val verAllVisitasUseCase: VerAllVisitasUseCase,
    private val borrerVisitasUseCase: BorrarVisitasUseCase,
    private val actualizarVisitaUseCase: ActualizarVisitaUseCase
    ): ViewModel() {
        private val _uiState: MutableLiveData<DetalleState> = MutableLiveData(DetalleState(visita = Visita()))
        val uiState: MutableLiveData<DetalleState> get() = _uiState

    fun getVisitas(nombre: String) {
        val visitas = verAllVisitasUseCase.invoke()
        val visita = visitas.find { it.nombre == nombre }

        if (visita == null){
            _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar("Error: Visita no encontrada"))
        } else {
            _uiState.value = _uiState.value?.copy(visita = visita)
        }
    }

    fun clickBorrar(visita: Visita){
        if (borrerVisitasUseCase.invoke(visita)) {
            _uiState.value = _uiState.value?.copy(visita = Visita(), event = UiEvent.Navigate("MainActivity"))
        } else {
            _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar("Error: No se pudo borrar la visita"))
        }
    }
    fun clickActualizar(visita: Visita) {
        if (actualizarVisitaUseCase.invoke(visita)) {
            _uiState.value = _uiState.value?.copy(event = UiEvent.Navigate("MainActivity"))
        } else {
            _uiState.value = _uiState.value?.copy(event = UiEvent.ShowSnackbar("Error: No se pudo actualizar la visita"))
        }
    }

    fun limpiarEvento() {
        _uiState.value = _uiState.value?.copy(event = null)
    }
}
class DetalleViewModelFactory(
    private val verAllVisitasUseCase: VerAllVisitasUseCase,
    private val borrerVisitasUseCase: BorrarVisitasUseCase,
    private val actualizarVisitaUseCase: ActualizarVisitaUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetalleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetalleViewModel(
                verAllVisitasUseCase,
                borrerVisitasUseCase,
                actualizarVisitaUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}