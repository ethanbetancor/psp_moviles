package com.example.ui.pantallaPersonas.pantallaAddPersona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Persona
import com.example.navigationhiltroom.databinding.FragmentAddPersonaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class addPersona : Fragment() {

    private var _binding: FragmentAddPersonaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddPersonaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPersonaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardarPersona.setOnClickListener {
            val dni = binding.etDni.text.toString()
            val nombre = binding.etNombre.text.toString()
            val edad = binding.etEdad.text.toString().toInt()

            if (dni.isNotEmpty() && nombre.isNotEmpty() && edad > 0) {
                val persona = Persona(
                    dni = dni,
                    nombre = nombre,
                    edad = edad
                )

                val guardado = viewModel.guardarPersona(persona)

                if (guardado) {
                    Toast.makeText(requireContext(), "Persona guardada correctamente", Toast.LENGTH_SHORT).show()
                    val action = addPersonaDirections.actionAddPersonaToListaPersona()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVolver.setOnClickListener {
            val action = addPersonaDirections.actionAddPersonaToListaPersona()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}