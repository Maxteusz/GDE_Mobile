package com.example.gdemobile

import com.example.gdemobile.Models.Contractor
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("MethodInvoker/InvokeServiceMethod")
    suspend fun getContractors(): Response<Contractor>
}