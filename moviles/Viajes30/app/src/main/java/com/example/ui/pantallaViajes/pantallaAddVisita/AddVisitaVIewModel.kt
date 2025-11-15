package com.example.ui.pantallaViajes.pantallaAddVisita

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.model.Visita
import com.example.domain.usecases.usecasesVisitas.AddVisitasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddVisitaVIewModel @Inject constructor(
    private val addVisitasUseCase: AddVisitasUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // LiveData con los nombres de participantes seleccionados
    private val _participantes = MutableLiveData<List<String>>(emptyList())
    val participantes: LiveData<List<String>> = _participantes

    init {
        // Recuperar los participantes de los argumentos de navegación
        savedStateHandle.get<Array<String>>("selectedPersonasNames")?.let { names ->
            _participantes.value = names.toList()
        }
    }

    fun setParticipantes(names: List<String>) {
        _participantes.value = names
    }

    fun guardarVisita(visita: Visita): Boolean {
        return addVisitasUseCase.invoke(visita)
    }
}