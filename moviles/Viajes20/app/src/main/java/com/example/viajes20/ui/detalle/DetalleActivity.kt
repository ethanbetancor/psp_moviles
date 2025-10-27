package com.example.viajes20.ui.detalle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viajes20.Domain.Modelo.Rating
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.Domain.UseCases.ActualizarVisitaUseCase
import com.example.viajes20.Domain.UseCases.BorrarVisitasUseCase
import com.example.viajes20.Domain.UseCases.VerAllVisitasUseCase
import com.example.viajes20.R
import com.example.viajes20.databinding.ActivityDetalleBinding
import com.example.viajes20.ui.commons.UiEvent
import com.example.viajes20.ui.lista.MainActivity
import com.google.android.material.snackbar.Snackbar

class DetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleBinding
    private val viewModel: DetalleViewModel by viewModels {
        DetalleViewModelFactory(
            VerAllVisitasUseCase(),
            BorrarVisitasUseCase(),
            ActualizarVisitaUseCase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            val nombreVisita = intent.getStringExtra("ciudad") ?: ""
            viewModel.getVisitas(nombreVisita)
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        eventos()
        observer()
    }

    private fun observer() {
        viewModel.uiState.observe(this) { state ->
            state?.let {
                it.event?.let { event ->
                    when (event) {
                        is UiEvent.ShowSnackbar -> {
                            Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG).show()
                        }

                        is UiEvent.Navigate -> {
                            val intent = Intent(this@DetalleActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    viewModel.limpiarEvento()
                }
                if (it.event == null) {
                    with(binding) {
                        CiudadText.setText(it.visita.nombre)
                        PaisText.setText(it.visita.pais)
                        FechaVisitaText.setText(it.visita.fecha)
                        MotivoText.setText(it.visita.motivo)
                        comentText.setText(it.visita.comentarios)

                        when (it.visita.rating) {
                            Rating.UNO -> ratingGroup.check(R.id.radio1)
                            Rating.DOS -> ratingGroup.check(R.id.radio2)
                            Rating.TRES -> ratingGroup.check(R.id.radio3)
                            Rating.CUATRO -> ratingGroup.check(R.id.radio4)
                            Rating.CINCO -> ratingGroup.check(R.id.radio5)
                            Rating.VACIO -> ratingGroup.clearCheck()
                        }
                    }
                }
            }
        }
    }

    private fun eventos() {
        with(binding) {
            btnVolver.setOnClickListener {
                val intent = Intent(this@DetalleActivity, MainActivity::class.java)
                startActivity(intent)
            }

            btnActualizar.setOnClickListener {
                val nombre = CiudadText.text.toString()
                val pais = PaisText.text.toString()
                val fecha = FechaVisitaText.text.toString()
                val motivo = MotivoText.text.toString()
                val comentarios = comentText.text.toString()

                val rating = when (ratingGroup.checkedRadioButtonId) {
                    R.id.radio1 -> Rating.UNO
                    R.id.radio2 -> Rating.DOS
                    R.id.radio3 -> Rating.TRES
                    R.id.radio4 -> Rating.CUATRO
                    R.id.radio5 -> Rating.CINCO
                    else -> Rating.VACIO
                }

                val visitaActualizada = Visita(
                    nombre = nombre,
                    pais = pais,
                    fecha = fecha,
                    motivo = motivo,
                    comentarios = comentarios,
                    rating = rating
                )
                viewModel.clickActualizar(visitaActualizada)
            }

            btnBorrar.setOnClickListener {
                viewModel.uiState.value?.visita?.let { visita ->
                    viewModel.clickBorrar(visita)
                }
            }
        }
    }
}
