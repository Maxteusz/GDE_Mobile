package com.example.gdemobile.apiConnect.enovaConnect.retrofit

import com.example.gdemobile.apiConnect.enovaConnect.ReceiveDto
import com.example.gdemobile.apiConnect.enovaConnect.RequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IRetrofitMethod {

    @POST("/api/MethodInvoker/InvokeServiceMethod")
    @Headers("Content-Type: application/json","Accept: application/json")
    @JvmSuppressWildcards
    suspend fun <X> getListData(@Body body: RequestDto): Response<ReceiveDto<X>>


}