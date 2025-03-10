package com.example.prueba.model

data class Items (
    val ticker: String,
    val target_from: String,
    val target_to: String,
    val company: String,
    val action: String,
    val brokerage: String,
    val rating_from: String,
    val rating_to: String,
    val time: String
)