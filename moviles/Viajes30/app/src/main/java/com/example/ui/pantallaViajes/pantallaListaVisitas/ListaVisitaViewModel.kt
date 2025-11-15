package com.example.ui.pantallaViajes.pantallaListaVisitas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Visita
import com.example.domain.usecases.usecasesVisitas.VerAllVisitasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListaVisitaViewModel @Inject constructor(
    private val verAllVisitasUseCase: VerAllVisitasUseCase
) : ViewModel() {

    private val _visitas = MutableLiveData<List<Visita>>()
    val visitas: LiveData<List<Visita>> = _visitas

    init {
        loadVisitas()
    }

    fun loadVisitas() {
        _visitas.value = verAllVisitasUseCase().toList()
    }
}
