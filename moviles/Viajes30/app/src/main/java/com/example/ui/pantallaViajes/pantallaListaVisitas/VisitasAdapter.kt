package com.example.ui.pantallaViajes.pantallaListaVisitas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Visita
import com.example.navigationhiltroom.databinding.ItemVisitaBinding

class VisitasAdapter(
    private val onItemClick: (Visita) -> Unit
) : ListAdapter<Visita, VisitasAdapter.VisitaViewHolder>(VisitaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitaViewHolder {
        val binding = ItemVisitaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VisitaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VisitaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VisitaViewHolder(
        private val binding: ItemVisitaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(visita: Visita) {
            binding.itCiudad.text = visita.ciudad
            binding.itFecha.text = visita.fecha

            binding.root.setOnClickListener {
                onItemClick(visita)
            }
        }
    }

    class VisitaDiffCallback : DiffUtil.ItemCallback<Visita>() {
        override fun areItemsTheSame(oldItem: Visita, newItem: Visita): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Visita, newItem: Visita): Boolean {
            return oldItem == newItem
        }
    }
}

