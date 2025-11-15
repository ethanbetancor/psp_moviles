package com.example.ui.pantallaPersonas.pantallaDetallePersona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationhiltroom.databinding.FragmentDetallePersonaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class detallePersona : Fragment() {
    private var _binding: FragmentDetallePersonaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetallePersonaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetallePersonaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupButtons()
    }

    private fun setupObservers() {
        viewModel.persona.observe(viewLifecycleOwner) { persona ->
            with(binding) {
                etDni.setText(persona.dni)
                etNombre.setText(persona.nombre)
                etEdad.setText(persona.edad.toString())

                etDni.isEnabled = false
                etNombre.isEnabled = false
                etEdad.isEnabled = false
            }
        }
    }

    private fun setupButtons() {
        binding.btnVolver.setOnClickListener {
            val action = detallePersonaDirections.actionDetallePersonaToListaPersona()
            findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}