package com.example.gdemobile

import com.example.gdemobile.models.Contractor
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("contractors")
    suspend fun getContractors(): Response<Contractor>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("auth/loginpassword")
    suspend fun getToken(@Body x: HashMap<String, String>): Response<String>
}