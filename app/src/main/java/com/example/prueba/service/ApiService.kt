package com.example.prueba.service

import com.example.prueba.model.LoginRequest
import com.example.prueba.model.LoginResponse
import com.example.prueba.model.StockRequest
import com.example.prueba.model.StockResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/user/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/user/stocks")
    fun Stocks(@Header("Authorization") authToken: String): Call<StockResponse>
}