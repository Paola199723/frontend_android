package com.example.prueba.model

data class RecomendatiosItems (
    val ticker:String,
    val company: String,
    val target_from: String,
    val target_to: String,
    val cambio_porcentual: Float
)