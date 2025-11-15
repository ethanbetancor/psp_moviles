package com.example.ui.pantallaViajes.pantallaDetalleVisita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Rating
import com.example.navigationhiltroom.databinding.FragmentDetalleVisitaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class detalleVisita : Fragment() {

    private var _binding: FragmentDetalleVisitaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetalleVisitaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleVisitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupButtons()
    }

    private fun setupObservers() {
        viewModel.visita.observe(viewLifecycleOwner) { visita ->
            with(binding) {
                // Mostrar datos de la visita
                CiudadText.setText(visita.ciudad)
                PaisText.setText(visita.pais)
                FechaVisitaText.setText(visita.fecha)
                MotivoText.setText(visita.motivo)
                comentText.setText(visita.comentarios)

                // Mostrar participantes
                if (visita.participantes.isEmpty()) {
                    textSelectedParticipants.text = "Participantes: Ninguno"
                } else {
                    textSelectedParticipants.text = "Participantes: ${visita.participantes.joinToString(", ")}"
                }

                // Marcar el rating correcto
                when (visita.rating) {
                    Rating.UNO -> radio1.isChecked = true
                    Rating.DOS -> radio2.isChecked = true
                    Rating.TRES -> radio3.isChecked = true
                    Rating.CUATRO -> radio4.isChecked = true
                    Rating.CINCO -> radio5.isChecked = true
                    else -> {}
                }

                // Hacer todos los campos de solo lectura
                CiudadText.isEnabled = false
                PaisText.isEnabled = false
                FechaVisitaText.isEnabled = false
                MotivoText.isEnabled = false
                comentText.isEnabled = false
                radio1.isEnabled = false
                radio2.isEnabled = false
                radio3.isEnabled = false
                radio4.isEnabled = false
                radio5.isEnabled = false
            }
        }
    }

    private fun setupButtons() {
        binding.btnVolver.setOnClickListener {
            val action = detalleVisitaDirections.actionDetalleVisitaToListaVisita()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}