package com.example.viajes20.ui.añadir

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viajes20.Domain.Modelo.Rating
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.R
import com.example.viajes20.databinding.ActivityAddBinding
import com.example.viajes20.ui.commons.UiEvent

class AñadirActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private val viewModel: AñadirViewModel by viewModels {
        AñadirViewModelFactory(

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        eventos(btnGuardar)
        observacion()

    }


    private fun eventos(btnGuardar: Button) {
        btnGuardar.setOnClickListener {
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

    }

    private fun observacion() {
        viewModel.state.observe(this) { state ->
            state?.let {
                it.event?.let { event ->
                    when (event) {
                        is UiEvent.ShowSnackbar -> {
                            Toast.makeText(this@AñadirActivity, event.message, Toast.LENGTH_LONG).show()
                        }
                        is UiEvent.Navigate -> {
                            val intent = Intent(this@AñadirActivity, com.example.viajes20.ui.lista.MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    viewModel.limpiar()
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

                it.error?.let { errorMsg ->
                    Toast.makeText(this@AñadirActivity, errorMsg, Toast.LENGTH_LONG).show()
                    viewModel.limpiar()
                }
            }
        }
    }
}