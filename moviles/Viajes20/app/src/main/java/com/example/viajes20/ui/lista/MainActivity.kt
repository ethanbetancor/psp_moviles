package com.example.viajes20.ui.lista

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viajes20.Domain.Modelo.Visita
import com.example.viajes20.Domain.UseCases.VerAllVisitasUseCase
import com.example.viajes20.R
import com.example.viajes20.databinding.ActivityMainBinding
import com.example.viajes20.ui.añadir.AñadirActivity
import com.example.viajes20.ui.commons.MarginItemDecoration
import com.example.viajes20.ui.detalle.DetalleActivity
import kotlin.getValue

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViajeAdapter


    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            VerAllVisitasUseCase()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        events()
        configureRecyclerView()
        observarState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getVisitas()
    }

    private fun observarState() {
        viewModel.uiState.observe(this@MainActivity) { state ->
            adapter.submitList(state.viajes)
        }
    }

    private fun configureRecyclerView() {

        adapter = ViajeAdapter(
            actions = object : ViajeAdapter.ViajeActions {
                override fun onItemClick(visita: Visita) {
                    navigateToDetail(visita.nombre)
                }

            })


        binding.listaVisitas.layoutManager = LinearLayoutManager(this)

        binding.listaVisitas.adapter = adapter

        binding.listaVisitas.addItemDecoration(
            MarginItemDecoration(

                resources.getDimensionPixelSize(
                    R.dimen.margin
                )
            )
        )
    }


    private fun navigateToDetail(ciudad: String) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("ciudad", ciudad)
        startActivity(intent)
    }

    private fun events() {
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AñadirActivity::class.java)
            startActivity(intent)
        }
    }
}