package com.example.gdemobile.apiConnect.enovaConnect

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitMethod {

    @POST("/api/MethodInvoker/InvokeServiceMethod")
    @Headers("Content-Type: application/json","Accept: application/json")
    @JvmSuppressWildcards
    suspend fun <X> getListData(@Body body: RequestDto): Response<ReceiveDto<X>>


}