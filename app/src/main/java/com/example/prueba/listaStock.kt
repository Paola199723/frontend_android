package com.example.prueba

import CustomAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.databinding.ActivityListaStockBinding

class listaStock : AppCompatActivity() {

    private lateinit var binding: ActivityListaStockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaStockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3","Elemento 1", "Elemento 2", "Elemento 3") // Datos

        val itemsmenu = listOf("Elemento 4", "Elemento 5", "Elemento 6","Elemento 4", "Elemento 5", "Elemento 6","Elemento 4", "Elemento 5", "Elemento 6","Elemento 4", "Elemento 5", "Elemento 6","Elemento 4", "Elemento 5", "Elemento 6") // Datos

        val adapter = CustomAdapter(items){position ->
            Toast.makeText(this, "Item en posición: $position", Toast.LENGTH_SHORT).show()
        }
        val adapter2 = CustomAdapter(itemsmenu){position ->
            Toast.makeText(this, "Item en posición: $position", Toast.LENGTH_SHORT).show()
        }
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = adapter

        binding.RecyclerViewMenu.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        binding.RecyclerViewMenu.adapter = adapter2

    }
}
