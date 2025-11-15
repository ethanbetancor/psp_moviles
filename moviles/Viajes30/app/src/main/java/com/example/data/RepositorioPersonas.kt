package com.example.data

import com.example.domain.model.Persona

class RepositorioPersonas {
    private val personas = mutableListOf<Persona>()

    init {
        personas.add(Persona("12345678A", "Ana García", 28))
        personas.add(Persona("23456789B", "Luis Pérez", 35))
        personas.add(Persona("34567890C", "Carlos Ruiz", 41))
        personas.add(Persona("45678901D", "María López", 22))
        personas.add(Persona("56789012E", "Sofía Martínez", 30))
        personas.add(Persona("67890123F", "Juan Torres", 27))
        personas.add(Persona("78901234G", "Lucía Sánchez", 33))
        personas.add(Persona("89012345H", "Miguel Fernández", 45))
        personas.add(Persona("90123456I", "Elena Gómez", 19))
        personas.add(Persona("01234567J", "Pablo Díaz", 38))
    }

    fun getPersona(index: Int) = personas[index]
    fun save(persona: Persona) = personas.add(persona)
    fun delete(persona: Persona): Boolean = personas.remove(persona)
    fun size() = personas.size

    fun actualizarPersona(persona: Persona): Boolean {
        val index = personas.indexOfFirst { it.dni == persona.dni }
        return if (index != -1) {
            personas[index] = persona
            true
        } else {
            false
        }
    }

    fun getAllPersonas(): List<Persona> = personas.toList()
}