package com.example.viajes20.ui.añadir

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.Domain.UseCases.AddVisitasUseCase
import com.example.viajes20.ui.commons.UiEvent
import java.lang.IllegalArgumentException

class AñadirViewModel: ViewModel() {
    private val _state: MutableLiveData<AñadirState> = MutableLiveData()
    val state: LiveData<AñadirState> get() = _state

    init {
        _state.value = AñadirState(visita = Visita(), error = null)
    }


    fun clickGuardar(visita: Visita) {
        if (AddVisitasUseCase().invoke(visita)) {
            _state.value =
                _state.value?.copy(visita = visita, event = UiEvent.Navigate("MainActivity"))

        } else {
            _state.value = _state.value?.copy(error = "error")
        }
    }

    fun limpiar() {
        _state.value = _state.value?.copy(error = null, event = null)
    }


}

class AñadirViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AñadirViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AñadirViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}