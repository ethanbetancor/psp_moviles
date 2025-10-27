package com.example.viajes20.ui.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.databinding.ItemViewBinding


class ViajeAdapter(
    val actions : ViajeActions,
) : ListAdapter<Visita, ViajeItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViajeItemViewHolder {
        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViajeItemViewHolder(binding,actions)
    }

    override fun onBindViewHolder(holder: ViajeItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<Visita>() {
        override fun areItemsTheSame(oldItem: Visita, newItem: Visita): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Visita, newItem: Visita): Boolean {
            return oldItem == newItem
        }
    }

    interface ViajeActions {
        fun onItemClick(visita: Visita)
    }
}
