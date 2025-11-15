package com.example.ui.pantallaViajes.pantallaSeleccionPersonas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Persona
import com.example.domain.usecases.usecasesPersonas.VerAllPersonasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeleccionPersonasViewModel @Inject constructor(
    private val verAllPersonasUseCase: VerAllPersonasUseCase
) : ViewModel() {

    private val _personas = MutableLiveData<List<Persona>>()
    val personas: LiveData<List<Persona>> = _personas

    // Lista simple de nombres seleccionados
    private val selectedNames = mutableSetOf<String>()

    init {
        loadPersonas()
    }

    fun loadPersonas() {
        _personas.value = verAllPersonasUseCase()
    }

    fun togglePersonaSelection(nombre: String, isSelected: Boolean) {
        if (isSelected) {
            selectedNames.add(nombre)
        } else {
            selectedNames.remove(nombre)
        }
    }

    fun getSelectedNames(): List<String> {
        return selectedNames.toList()
    }
}