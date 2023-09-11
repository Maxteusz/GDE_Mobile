package com.example.gdemobile.enovaConnect

import android.util.Log
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.RetrofitMethod
import com.example.gdemobile.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.reflect.typeOf

class ConnectService(
    val stateResponse: StateResponse?,
    val connectionParameters: IConnectEnovaMethod
) {

   suspend fun <T> makeConnectionForListData(): T? {
       try {
           val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
           var result = quotesApi.getListData<T?>(getBody(connectionParameters))
           if (result.code() == 200) {

               stateResponse?.OnSucces()
               return result.body()?.resultInstance
           }
           return null


       } catch (timeout: SocketTimeoutException) {
           stateResponse?.OnError()
           Log.e(LogTag.timeoutException, timeout.message.toString())
           return null

       } catch (exception: ConnectException) {
           stateResponse?.OnError()
           Log.e(LogTag.connectException, exception.message.toString())
           return null

       } catch (exception: Exception) {
           stateResponse?.OnError()
           Log.e(LogTag.unknownException, exception.message.toString())
           return null
       }


   }


    private fun getBody(
        connectionParameters: IConnectEnovaMethod,
        paginationDto: PaginationDto = PaginationDto(0, 3)
    ): RequestDto<PaginationDto> {
        val body = RequestDto<PaginationDto>(connectionParameters)
        body.methodArgsDto = RequestDto.MethodArgs()
        body.methodArgsDto?.dto?.pagination = paginationDto
        return body;
    }

}


