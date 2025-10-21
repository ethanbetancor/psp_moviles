package com.example.primer_proyecto.ui.pantallamain

import android.R.attr.rating
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primer_proyecto.R
import com.example.primer_proyecto.databinding.ActivityMainBinding
import com.example.primer_proyecto.domain.modelo.Rating
import com.example.primer_proyecto.domain.modelo.Visita
import com.example.primer_proyecto.domain.usecases.AddVisitasUseCase


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var add: AddVisitasUseCase

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) ///
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        eventos()
        observacion()

    }


    private fun eventos() {
        binding.btnGuardar.setOnClickListener {
            val rating: Rating = when (binding.ratingGroup.checkedRadioButtonId) {
                R.id.radio1 -> Rating.UNO
                R.id.radio2 -> Rating.DOS
                R.id.radio3 -> Rating.TRES
                R.id.radio4 -> Rating.CUATRO
                R.id.radio5 -> Rating.CINCO
                else -> {
                    Rating.VACIO
                }
            }
            val visita = Visita(
                nombre = binding.CiudadText.text.toString(),
                pais = binding.PaisText.text.toString(),
                fecha = binding.FechaVisitaText.text.toString(),
                motivo = binding.MotivoText.text.toString(),
                comentarios = binding.comentText.text.toString(),
                rating = rating
            )
            viewModel.clickGuardar(visita)


        }

        binding.btnLimpiar.setOnClickListener {

            viewModel.clickLimpar()
        }

        binding.btnSiguiente.setOnClickListener {
            viewModel.clickSiguiente()
        }
        binding.btnAnterior.setOnClickListener {
            viewModel.clickAnterior()
        }
        binding.btnBorrar.setOnClickListener {
            val rating: Rating = when (binding.ratingGroup.checkedRadioButtonId) {
                R.id.radio1 -> Rating.UNO
                R.id.radio2 -> Rating.DOS
                R.id.radio3 -> Rating.TRES
                R.id.radio4 -> Rating.CUATRO
                R.id.radio5 -> Rating.CINCO
                else -> {
                    Rating.VACIO
                }
            }
            val visita = Visita(
                nombre = binding.CiudadText.text.toString(),
                pais = binding.PaisText.text.toString(),
                fecha = binding.FechaVisitaText.text.toString(),
                motivo = binding.MotivoText.text.toString(),
                comentarios = binding.comentText.text.toString(),
                rating = rating
            )
            viewModel.clickBorrar(visita)
        }

        binding.btnActualizar.setOnClickListener {
            val rating: Rating = when (binding.ratingGroup.checkedRadioButtonId) {
                R.id.radio1 -> Rating.UNO
                R.id.radio2 -> Rating.DOS
                R.id.radio3 -> Rating.TRES
                R.id.radio4 -> Rating.CUATRO
                R.id.radio5 -> Rating.CINCO
                else -> {
                    Rating.VACIO
                }
            }
            val visita = Visita(
                nombre = binding.CiudadText.text.toString(),
                pais = binding.PaisText.text.toString(),
                fecha = binding.FechaVisitaText.text.toString(),
                motivo = binding.MotivoText.text.toString(),
                comentarios = binding.comentText.text.toString(),
                rating = rating
            )
            viewModel.clickActualizar(visita)
        }

    }


    private fun observacion() {
        viewModel.state.observe(this) { state ->
            state.error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.limpiar()
            }

                binding.CiudadText.setText(state.visita.nombre)
                binding.PaisText.setText(state.visita.pais)
                binding.FechaVisitaText.setText(state.visita.fecha)
                binding.MotivoText.setText(state.visita.motivo)
                binding.comentText.setText(state.visita.comentarios)
                when (state.visita.rating) {
                    Rating.UNO -> binding.radio1.isChecked = true
                    Rating.DOS -> binding.radio2.isChecked = true
                    Rating.TRES -> binding.radio3.isChecked = true
                    Rating.CUATRO -> binding.radio4.isChecked = true
                    Rating.CINCO -> binding.radio5.isChecked = true
                    else -> binding.ratingGroup.clearCheck()
                }
                binding.Contador.setText("${state.posicion}/${state.total}")
                binding.btnSiguiente.isEnabled = state.siguiente
                binding.btnAnterior.isEnabled = state.anterior


        }


    }
}