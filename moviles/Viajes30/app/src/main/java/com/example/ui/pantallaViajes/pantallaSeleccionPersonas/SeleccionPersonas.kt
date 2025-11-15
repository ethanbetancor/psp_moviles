package com.example.ui.pantallaViajes.pantallaSeleccionPersonas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationhiltroom.databinding.FragmentSeleccionPersonasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeleccionPersonas : Fragment() {
    private var _binding: FragmentSeleccionPersonasBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SeleccionPersonasViewModel by viewModels()
    private lateinit var adapter: SeleccionPersonasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeleccionPersonasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupButtons()
    }

    private fun setupRecyclerView() {
        adapter = SeleccionPersonasAdapter { nombre, isSelected ->
            viewModel.togglePersonaSelection(nombre, isSelected)
        }

        binding.recyclerViewSeleccion.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SeleccionPersonas.adapter
        }
    }

    private fun setupObservers() {
        viewModel.personas.observe(viewLifecycleOwner) { personas ->
            adapter.submitList(personas)
            adapter.setSelectedNames(viewModel.getSelectedNames())
        }
    }

    private fun setupButtons() {
        binding.btnConfirmar.setOnClickListener { val selectedNames = viewModel.getSelectedNames()

            val action = SeleccionPersonasDirections.actionSeleccionPersonasToAddVisita(
                selectedPersonasNames = selectedNames.toTypedArray()
            )
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}