package com.example.ui.pantallaViajes.pantallaListaVisitas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationhiltroom.databinding.FragmentListaVisitaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class listaVisita : Fragment() {

    private var _binding: FragmentListaVisitaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListaVisitaViewModel by viewModels()

    private lateinit var visitasAdapter: VisitasAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaVisitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.floatingActionButton.setOnClickListener {
            val action = listaVisitaDirections.actionListaVisitaToAddVisita()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar la lista cada vez que volvemos a esta pantalla
        viewModel.loadVisitas()
    }

    private fun setupRecyclerView() {
        visitasAdapter = VisitasAdapter { visita ->
            // Navegar al detalle de la visita pasando el ID
            val action = listaVisitaDirections.actionListaVisitaToDetalleVisita(visita.id)
            findNavController().navigate(action)
        }

        binding.recyclerView2.apply {
            adapter = visitasAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers() {
        viewModel.visitas.observe(viewLifecycleOwner) { visitas ->
            visitasAdapter.submitList(visitas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}