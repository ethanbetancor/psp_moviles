package com.example.ui.pantallaViajes.pantallaAddVisita


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.Rating
import com.example.domain.model.Visita
import com.example.navigationhiltroom.R
import com.example.navigationhiltroom.databinding.FragmentAddVisitaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class addVisita : Fragment() {

    private var _binding: FragmentAddVisitaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddVisitaVIewModel by viewModels()
    private val args: AddVisitaArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddVisitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupButtons()

        // Si volvemos de la pantalla de selección, actualizar la lista
        args.selectedPersonasNames?.let { names ->
            viewModel.setParticipantes(names.toList())
        }
    }

    private fun setupObservers() {
        viewModel.participantes.observe(viewLifecycleOwner) { nombres ->
            if (nombres.isEmpty()) {
                binding.textSelectedParticipants.text = getString(R.string.participantes_ninguno)
            } else {
                val participantesText = nombres.joinToString(", ")
                binding.textSelectedParticipants.text = "Participantes: $participantesText"
            }
        }
    }

    private fun setupButtons() {
        binding.btnParticipantes.setOnClickListener {
            val action = AddVisitaDirections.actionAddVisitaToSeleccionPersonas()
            findNavController().navigate(action)
        }

        binding.btnGuardar.setOnClickListener {
            val ciudad = binding.CiudadText.text.toString()
            val pais = binding.PaisText.text.toString()
            val fecha = binding.FechaVisitaText.text.toString()
            val motivo = binding.MotivoText.text.toString()
            val comentarios = binding.comentText.text.toString()

            // Validar campos obligatorios
            if (ciudad.isEmpty() || pais.isEmpty()) {
                Toast.makeText(requireContext(), "Ciudad y País son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener rating seleccionado
            val rating = when {
                binding.radio1.isChecked -> Rating.UNO
                binding.radio2.isChecked -> Rating.DOS
                binding.radio3.isChecked -> Rating.TRES
                binding.radio4.isChecked -> Rating.CUATRO
                binding.radio5.isChecked -> Rating.CINCO
                else -> Rating.VACIO
            }

            // Obtener la lista de participantes del ViewModel (acceso al .value de LiveData)
            val participantes = viewModel.participantes.value ?: emptyList()

            val visita = Visita(
                ciudad = ciudad,
                pais = pais,
                fecha = fecha,
                motivo = motivo,
                participantes = participantes,
                comentarios = comentarios,
                rating = rating
            )

            val guardado = viewModel.guardarVisita(visita)

            if (guardado) {
                Toast.makeText(requireContext(), "Viaje guardado correctamente", Toast.LENGTH_SHORT).show()
                val action = AddVisitaDirections.actionAddVisitaToListaVisita()
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Error al guardar el viaje", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}