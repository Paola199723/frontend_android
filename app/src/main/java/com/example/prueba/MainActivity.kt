package com.example.prueba

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba.databinding.ActivityMainBinding
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonIniciar = findViewById<Button>(R.id.button_iniciar)

        buttonIniciar.setOnClickListener {
            validateEmail()
        }
    }

    private fun validateEmail() {
        val email_text = findViewById<EditText>(R.id.text_view_gmail)
        val email = email_text.text.toString()
        if (!validarEmail(email)) {
            email_text.setError("Email no válido")
            // Aquí agregas la lógica de validación
        }else{
            val intent = Intent(this, listaStock::class.java)
            startActivity(intent)
        }
    }

    fun validarEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}