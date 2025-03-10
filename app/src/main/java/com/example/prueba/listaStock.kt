package com.example.prueba

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba.databinding.ActivityListaStockBinding
import com.example.prueba.model.LoginRequest
import com.example.prueba.model.LoginResponse
import com.example.prueba.model.RecomendationResponse
import com.example.prueba.model.RecomendatiosItems
import com.example.prueba.model.StockRequest
import com.example.prueba.model.StockResponse
import com.example.prueba.service.ApiClient
import com.example.prueba.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections.list
import kotlin.math.log

class listaStock : AppCompatActivity() {

    private lateinit var binding: ActivityListaStockBinding
    private var selectedPosition: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaStockBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) // Asegúrate de tener una Toolbar en tu layout

        // Recibir el token del Intent

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val authToken = intent.getParcelableExtra<LoginResponse>("loginData")?.auth_token

        return when (item.itemId) {
            R.id.menu_stock -> {
                authToken?.let { GuardarLista(it) }
                true
            }
            R.id.menu_recomendacion -> {
                authToken?.let { GuardarListaRecomendation(it) }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun GuardarLista(authToken: String) {
        val formattedToken = "Bearer $authToken" // 🔥 Se agrega el espacio correctamente

        ApiClient.instance.Stocks(formattedToken).enqueue(object : Callback<StockResponse> {
            override fun onResponse(call: Call<StockResponse>, response: Response<StockResponse>) {
                if (response.isSuccessful) {
                    val stockResponse = response.body()
                    // Verificar si la respuesta no es nula antes de acceder a sus datos
                    stockResponse?.total_items?.let { items ->
                        val adapter = CustomAdapter(this@listaStock,items.toList())
                        binding.RecyclerView.layoutManager = LinearLayoutManager(this@listaStock)
                        binding.RecyclerView.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@listaStock, "No existen datos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<StockResponse>, t: Throwable) {
                Toast.makeText(this@listaStock, "Error de conexión", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun GuardarListaRecomendation(authToken: String) {
        val formattedToken = "Bearer $authToken" // 🔥 Se agrega el espacio correctamente
        Toast.makeText(this@listaStock, "token"+formattedToken, Toast.LENGTH_LONG).show()

        ApiClient.instance.Recomendations().enqueue(object : Callback<List<RecomendatiosItems>> {
            override fun onResponse(call: Call<List<RecomendatiosItems>>, response: Response<List<RecomendatiosItems>>) {
                if (response.isSuccessful) {
                    val items = response.body() ?: emptyList()
                    val adapter = CustomAdapterRecomendations(this@listaStock, items)
                    binding.RecyclerView.layoutManager = LinearLayoutManager(this@listaStock)
                    binding.RecyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@listaStock, "No existen datos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<RecomendatiosItems>>, t: Throwable) {
                Toast.makeText(this@listaStock, "Error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }

        })
    }
}

