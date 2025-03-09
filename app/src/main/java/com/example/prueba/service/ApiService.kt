package com.example.prueba.service

import com.example.prueba.model.LoginRequest
import com.example.prueba.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/user/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}