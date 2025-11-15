package com.example.ui.pantallaPersonas.pantallaListaPersonas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Persona
import com.example.navigationhiltroom.databinding.ItemPersonaBinding

class PersonasAdapter(
    private val onItemClick: (Persona) -> Unit
) : ListAdapter<Persona, PersonasAdapter.PersonaViewHolder>(PersonaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PersonaViewHolder(
        private val binding: ItemPersonaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(persona: Persona) {
            binding.itNombre.text = persona.nombre
            binding.itEdad.text = "${persona.edad} años"

            binding.root.setOnClickListener {
                onItemClick(persona)
            }
        }
    }

    class PersonaDiffCallback : DiffUtil.ItemCallback<Persona>() {
        override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem.dni == newItem.dni
        }

        override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem == newItem
        }
    }
}

