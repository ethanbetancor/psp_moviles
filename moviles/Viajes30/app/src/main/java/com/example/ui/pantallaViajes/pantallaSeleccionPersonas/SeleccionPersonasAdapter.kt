package com.example.ui.pantallaViajes.pantallaSeleccionPersonas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Persona
import com.example.navigationhiltroom.databinding.ItemPersonaSeleccionBinding

class SeleccionPersonasAdapter(
    private val onPersonaChecked: (String, Boolean) -> Unit
) : ListAdapter<Persona, SeleccionPersonasAdapter.PersonaViewHolder>(PersonaDiffCallback()) {

    // Set simple de nombres seleccionados
    private val selectedNames = mutableSetOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaSeleccionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setSelectedNames(names: List<String>) {
        selectedNames.clear()
        selectedNames.addAll(names)
    }

    inner class PersonaViewHolder(
        private val binding: ItemPersonaSeleccionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(persona: Persona) {
            binding.apply {
                tvNombrePersona.text = persona.nombre
                tvDniPersona.text = persona.dni

                checkboxPersona.isChecked = selectedNames.contains(persona.nombre)
                checkboxPersona.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectedNames.add(persona.nombre)
                    } else {
                        selectedNames.remove(persona.nombre)
                    }
                    onPersonaChecked(persona.nombre, isChecked)
                }
                root.setOnClickListener {
                    checkboxPersona.isChecked = !checkboxPersona.isChecked
                }
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
