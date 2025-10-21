package com.example.viajes20.Ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.Domain.UseCases.ActualizarVisitaUseCase
import com.example.viajes20.Domain.UseCases.AddVisitasUseCase
import com.example.viajes20.Domain.UseCases.BorrarVisitasUseCase
import com.example.viajes20.Domain.UseCases.SizeVisitasUseCase
import com.example.viajes20.Domain.UseCases.VerVisitasUseCase
import java.lang.IllegalArgumentException
import kotlin.jvm.java

class MainViewModel: ViewModel() {
    private val _state: MutableLiveData<MainState> = MutableLiveData()
    val state: LiveData<MainState> get() = _state

    init {
        _state.value = MainState(visita = Visita(),posicion = 0, total = SizeVisitasUseCase().invoke())
        checkBotones()
    }


    fun clickGuardar(visita: Visita) {
        val total = _state.value?.total ?: 0


        if (AddVisitasUseCase().invoke(visita)) {
            _state.value = _state.value?.copy(visita = visita, total = total +1, error = "La visita se ha guardado correctamente")
        } else {
            _state.value = _state.value?.copy(error = "Error: No se pudo guardar la visita")
        }
        checkBotones()
    }

    fun clickLimpar(){
        _state.value = _state.value?.copy(visita = Visita())

    }

    fun clickBorrar(visita: Visita) {

        if (BorrarVisitasUseCase().invoke(visita)) {
            val nuevoTotal = SizeVisitasUseCase().invoke()
            val posicionActual = _state.value?.posicion ?: 0
            if (nuevoTotal == 0) {
                _state.value = _state.value?.copy(
                    visita = Visita(),
                    total = 0,
                    posicion = 0,
                    error = "Última visita borrada. La lista está vacía."
                )
            }
            else if (posicionActual > nuevoTotal) {

                val ultimaVisita = VerVisitasUseCase().invoke(nuevoTotal - 1)
                _state.value = _state.value?.copy(
                    visita = ultimaVisita,
                    total = nuevoTotal,
                    posicion = nuevoTotal,
                    error = "Visita borrada correctamente."
                )
            }else {

                val visitaActualizada = VerVisitasUseCase().invoke(posicionActual - 1)
                _state.value = _state.value?.copy(
                    visita = visitaActualizada,
                    total = nuevoTotal,
                    posicion = posicionActual,
                    error = "Visita borrada correctamente."
                )
            }
            checkBotones()
        } else {
            _state.value = _state.value?.copy(error = "Error: No se pudo borrar la visita")
        }
    }

    fun clickActualizar(visita: Visita) {
        if (ActualizarVisitaUseCase().invoke(visita)) {
            _state.value = _state.value?.copy(visita = visita, error = "La visita se ha actualizado correctamente")
        } else {
            _state.value = _state.value?.copy(error = "Error: No se pudo actualizar la visita")
        }


        checkBotones()
    }


    fun limpiar() {

        _state.value = _state.value?.copy(error = null)

    }

    fun checkBotones() {
        val indice = _state.value?.posicion ?: 0
        val total = _state.value?.total ?: 0

        if (indice <= 0) {
            _state.value = _state.value?.copy(siguiente = true, anterior = false, visita = Visita())
            return
        }
        if (indice >= total) {
            _state.value = _state.value?.copy(siguiente = false, anterior = true)
            return
        }
        _state.value = _state.value?.copy(siguiente = true, anterior = true)
    }

    fun clickSiguiente() {
        val indice = _state.value?.posicion ?: 0

        val visita = VerVisitasUseCase().invoke(indice)
        _state.value = _state.value?.copy(visita = visita, posicion = indice + 1)

        checkBotones()
    }

    fun clickAnterior() {
        val indiceActual = _state.value?.posicion ?: 0
        val nuevoIndice = indiceActual -1

        if (nuevoIndice >= 0) {
            if (nuevoIndice == 0) {
                _state.value = _state.value?.copy(visita = Visita(), posicion = 0)
            } else {
                val visita = VerVisitasUseCase().invoke(nuevoIndice - 1)
                _state.value = _state.value?.copy(visita = visita, posicion = nuevoIndice)
            }
        }

        checkBotones()
    }
}


class MainViewModelFactory(

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}