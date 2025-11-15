package com.example.ui.pantallaPersonas.pantallaListaPersonas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationhiltroom.R
import com.example.navigationhiltroom.databinding.FragmentListaPersonaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class listaPersona : Fragment() {

    private var _binding: FragmentListaPersonaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListaPersonasViewModel by viewModels()

    private lateinit var personasAdapter: PersonasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaPersonaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()


        binding.floatingActionButton.setOnClickListener {
            val action = listaPersonaDirections.actionListaPersonaToAddPersona()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista cada vez que volvemos a esta pantalla
        viewModel.loadPersonas()
    }

    private fun setupRecyclerView() {
        personasAdapter = PersonasAdapter { persona ->
            // Navegar al detalle de la persona pasando el índice
            val personas = viewModel.personas.value ?: emptyList()
            val index = personas.indexOf(persona)
            if (index != -1) {
                val action = listaPersonaDirections.actionListaPersonaToDetallePersona(index)
                findNavController().navigate(action)
            }
        }

        binding.recyclerView2.apply {
            adapter = personasAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        viewModel.personas.observe(viewLifecycleOwner) { personas ->
            personasAdapter.submitList(personas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}