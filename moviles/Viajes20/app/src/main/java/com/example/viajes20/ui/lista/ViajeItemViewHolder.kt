package com.example.viajes20.ui.lista

import androidx.recyclerview.widget.RecyclerView
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.databinding.ItemViewBinding

class ViajeItemViewHolder(
    private val binding: ItemViewBinding,
    val actions: ViajeAdapter.ViajeActions
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Visita){
        with(binding) {
            ciudad.text = item.nombre
            fecha.text = item.fecha

            root.setOnClickListener {
                actions.onItemClick(item)
            }
        }
    }
}