package com.example.viajes20.ui.commons

sealed interface UiEvent{
    data class Navigate(val route: String): UiEvent
    data class ShowSnackbar(val message: String): UiEvent
}