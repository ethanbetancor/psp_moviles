package com.example.ui.pantallaPersonas.pantallaDetallePersona

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.model.Persona
import com.example.domain.usecases.usecasesPersonas.VerPersonaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetallePersonaViewModel @Inject constructor(
    private val verPersonaUseCase: VerPersonaUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _persona = MutableLiveData<Persona>()
    val persona: LiveData<Persona> = _persona

    init {
        // Obtener el ID de la persona de los argumentos de navegación
        val personaId = savedStateHandle.get<Int>("personaId") ?: 0
        loadPersona(personaId)
    }

    private fun loadPersona(id: Int) {
        _persona.value = verPersonaUseCase(id)
    }
}