package com.example.ui.pantallaPersonas.pantallaListaPersonas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Persona
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.domain.usecases.usecasesPersonas.VerAllPersonasUseCase
import javax.inject.Inject

@HiltViewModel
class ListaPersonasViewModel @Inject constructor(
    private val verAllPersonasUseCase: VerAllPersonasUseCase
) : ViewModel() {

    private val _personas = MutableLiveData<List<Persona>>()
    val personas: LiveData<List<Persona>> = _personas

    init {
        loadPersonas()
    }

    fun loadPersonas() {
        _personas.value = verAllPersonasUseCase().toList()
    }

}