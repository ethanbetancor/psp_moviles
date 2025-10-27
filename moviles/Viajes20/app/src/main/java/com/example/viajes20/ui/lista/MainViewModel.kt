package com.example.viajes20.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viajes20.Domain.UseCases.VerAllVisitasUseCase

class MainViewModel(
    private val getVisitas: VerAllVisitasUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    init {
        getVisitas()
    }

    fun getVisitas() {
        _uiState.value = _uiState.value?.copy(viajes = getVisitas.invoke())
    }


}


class MainViewModelFactory(

    private val getVisitas: VerAllVisitasUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                getVisitas,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}