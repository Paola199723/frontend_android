package com.example.prueba

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.prueba.databinding.ActivityMainBinding
import com.example.prueba.model.LoginRequest
import com.example.prueba.model.LoginResponse
import com.example.prueba.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonIniciar = findViewById<Button>(R.id.btnLogin)
        /*
        route.POST("/user/login", controller.StartPage)
	    route.GET("/user/stocks", controller.Listpage)
	    route.GET("/user/recomendations", controller.RecomendationsList)
	    */
        buttonIniciar.setOnClickListener {
            validateEmail()
        }
    }

    private fun validateEmail() {
        val email_text = findViewById<EditText>(R.id.etEmail)
        val email = email_text.text.toString()
        if (!validarEmail(email)) {
            email_text.setError("Email no válido")
            // Aquí agregas la lógica de validación
        }else{
            ObtenerToken(email)
        }
    }

    fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    fun ObtenerToken(email: String){
        val loginRequest = LoginRequest(email, "' OR  password != '")

        ApiClient.instance.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(this@MainActivity, "Token: ${loginResponse?.auth_token}", Toast.LENGTH_LONG).show()

                    val intent = Intent(this@MainActivity, listaStock::class.java)
                    intent.putExtra("loginData", loginResponse)
                    startActivity(intent)

                    //login correcto
                } else {
                    Toast.makeText(this@MainActivity, "no existe", Toast.LENGTH_LONG).show()

                    //email fallo o no existe

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //error de internet
                Toast.makeText(this@MainActivity, "Error Internet", Toast.LENGTH_LONG).show()

            }
        })
    }

}
