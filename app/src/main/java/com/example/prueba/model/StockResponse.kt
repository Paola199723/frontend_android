package com.example.prueba.model


data class StockResponse (
    val message: String,
    val next_page: String,
    val total_items: Array<Items>
)