package com.example.viajes20.ui.commons

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Decoración para añadir márgenes entre elementos de un RecyclerView.
 *
 * @param spaceSize Tamaño del espacio en píxeles entre elementos
 */
class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {

    /**
     * Establece los márgenes para cada elemento del RecyclerView.
     * El primer elemento tiene margen superior adicional.
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            // Si es el primer elemento, añadir margen superior
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceSize
            }

            // Márgenes laterales e inferior para todos los elementos
            left = spaceSize
            right = spaceSize
            bottom = spaceSize
        }
    }
}