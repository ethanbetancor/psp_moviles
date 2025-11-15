package com.example.ui.pantallaPersonas.pantallaAddPersona

import androidx.lifecycle.ViewModel
import com.example.domain.model.Persona
import com.example.domain.usecases.usecasesPersonas.AddPersonasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPersonaViewModel @Inject constructor(
    private val addPersonasUseCase: AddPersonasUseCase
) : ViewModel() {

    fun guardarPersona(persona: Persona): Boolean {
        return addPersonasUseCase.invoke(persona)
    }
}