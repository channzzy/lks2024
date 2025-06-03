package com.example.lksmarket.api

import com.example.lksmarket.request.LoginRequest
import com.example.lksmarket.request.RegisterRequest
import com.example.lksmarket.response.InvoiceResponse
import com.example.lksmarket.response.LoginResponse
import com.example.lksmarket.response.ProductResponse
import com.example.lksmarket.response.ProfileResponse
import com.example.lksmarket.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type:application/json", "Accept:application/json")
    @POST("register")
    fun  register(
        @Body registerRequest: RegisterRequest
    ) : Call<RegisterResponse>

    @Headers("Content-Type:application/json", "Accept:application/json")
    @POST("auth/login")
    fun  login(
        @Body loginRequest: LoginRequest
    ) : Call<LoginResponse>

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("auth/me")
    fun  getProfile(
        @Header("Authorization") token : String
    ) : Call<ProfileResponse>

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("products")
    fun  getProducts(
        @Header("Authorization") token : String
    ) : Call<ProductResponse>

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("invoices")
    fun  getInvoice(
        @Header("Authorization") token : String
    ) : Call<InvoiceResponse>
}