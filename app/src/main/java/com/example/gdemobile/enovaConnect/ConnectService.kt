package com.example.gdemobile.enovaConnect

import android.util.Log
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.RetrofitMethod
import com.example.gdemobile.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import java.net.ConnectException
import java.net.SocketTimeoutException
import kotlin.reflect.typeOf

class ConnectService(
    val stateResponse: StateResponse?,
    val connectionParameters: IConnectEnovaMethod
) {

   suspend fun <T> makeConnectionForListData(): List<T> {
        try {

            val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)

            var result = quotesApi.getListData<String>(getBody(connectionParameters))
            if (result.code() == 200) {

                stateResponse?.OnSucces()
                Log.i("fffff",result.body()?.resultInstance?.toString()!!)
                return result.body()?.resultInstance
            }
            return emptyList()

        } catch (timeout: SocketTimeoutException) {
            stateResponse?.OnError()
            return null
            Log.e(LogTag.timeoutException, timeout.message.toString())
        } catch (exception: ConnectException) {
            stateResponse?.OnError()
            return null
            Log.e(LogTag.connectException, exception.message.toString())
        } catch (exception: java.lang.Exception) {
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


