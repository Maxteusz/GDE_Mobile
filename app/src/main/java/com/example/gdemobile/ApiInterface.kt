package com.example.gdemobile

import com.example.gdemobile.config.Config
import com.example.gdemobile.config.Config.Companion.tokenApi
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.DocumentDefinition
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("contractors")
    suspend fun getContractors(@Header("AuthToken") apiKey : String): Response<ArrayList<Contractor>>

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("dokdefs")
    suspend fun getDocumentDefinitions(@Header("AuthToken") apiKey : String): Response<ArrayList<DocumentDefinition>>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("auth/loginpassword")
    suspend fun getToken(@Body loginParams: HashMap<String, String>): Response<String>


}