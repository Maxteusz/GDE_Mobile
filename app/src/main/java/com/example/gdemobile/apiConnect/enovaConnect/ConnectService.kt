package com.example.gdemobile.apiConnect.enovaConnect

import android.util.Log
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import java.net.ConnectException
import java.net.SocketTimeoutException

class ConnectService(
    val stateResponse: StateResponse?,
) {

    suspend fun <X : Any> makeConnectionForListData(connectionParameters: IConnectEnovaMethod): X? {
        try {
            stateResponse?.OnLoading()

            val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
            var result = quotesApi.getListData<X>(RequestDto(connectionParameters))
            if (result.code() == 200) {
                Log.i(LogTag.enovaApiNameMethod,connectionParameters.methodName)
                Log.i(LogTag.enovaApiMessage,result.body()!!.exceptionMessage)
                Log.i(LogTag.enovaApiIsExcpetion,result.body()!!.isException.toString())
                Log.i(LogTag.enovaApiIsEmpty,result.body()!!.isEmpty.toString())
                Log.i(LogTag.enovaApiResultInstance,result.body()!!.resultInstance.toString())

                if(result.body()?.isException == false) {
                    stateResponse?.OnSucces()
                    return result.body()?.resultInstance
                }
            }
            stateResponse?.OnError()
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

}

