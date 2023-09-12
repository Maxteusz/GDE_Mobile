package com.example.gdemobile

import com.example.gdemobile.enovaConnect.ReceiveDto
import com.example.gdemobile.enovaConnect.RequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitMethod {

 /*   @Headers("Content-Type: application/json","Accept: application/json")
    @GET("contractors")
    suspend fun getContractors(@Header("AuthToken") apiKey : String): Response<ArrayList<Contractor>>

    @Headers("Content-Type: application/json","Accept: application/json")

    @GET("dokdefs")
    suspend fun getAllDocumentDefinitions(@Header("AuthToken") apiKey : String, @Query("pagesize") pagesize : Int = 10000): Response<ArrayList<DocumentDefinition>>

    @Headers("Content-Type: application/json","Accept: application/json")
    @POST("auth/loginpassword")
    suspend fun getToken(@Body loginParams: HashMap<String, String>): Response<String>

    @Headers("Content-Type: application/json","Accept: application/json")
    @GET("contractors")
    suspend fun getCargoFromApi(@Header("AuthToken") apiKey : String, @Query("name") cargoName : String): Response<DocumentPosition>
*/

    @POST("/api/MethodInvoker/InvokeServiceMethod")
    @Headers("Content-Type: application/json","Accept: application/json")
    suspend fun <T> getListData(@Body body: RequestDto<RequestDto.PaginationDto>): Response<ReceiveDto<T>>



}