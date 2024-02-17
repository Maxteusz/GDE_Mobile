package com.example.gdemobile.apiConnect.enovaConnect

import android.util.Log
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.JsonSerializer
import com.example.gdemobile.utils.LogTag
import com.google.gson.Gson
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException

class ConnectService(
    val stateResponse: IStateResponse?,
) {

    suspend fun <X : Any> makeConnection(connectionParameters: IConnectEnovaMethod): X? {
        try {
            stateResponse?.OnLoading()
Log.i(LogTag.enovaApiBodyRequest, Gson().toJson(connectionParameters))
            val quotesApi = RetrofitClient().getInstance().create(IRetrofitMethod::class.java)
            var result = quotesApi.getListData<X>(RequestDto(connectionParameters))
            if (result.code() == 200) {
                Log.i(LogTag.enovaApiNameMethod, connectionParameters.methodName)
                Log.i(LogTag.enovaApiMessage, result.body()!!.exceptionMessage)
                Log.i(LogTag.enovaApiIsExcpetion, result.body()!!.isException.toString())
                Log.i(LogTag.enovaApiIsEmpty, result.body()!!.isEmpty.toString())
                Log.i(LogTag.enovaApiResultInstance, result.body()!!.resultInstance.toString())

                if (result.body()?.isException == false) {
                    var result = result.body()?.resultInstance
                    stateResponse?.OnSucces()
                    return result
                }
                result.body()?.exceptionMessage?.let { stateResponse?.OnError(it) }
            }

            return null

        } catch (timeout: SocketTimeoutException) {
            stateResponse?.OnError("Serwer nie odpowiada")
            Log.e(LogTag.timeoutException, timeout.message.toString())
            return null

        } catch (exception: ConnectException) {
            stateResponse?.OnError("Sprawdź wpisane dane do połączenia")
            Log.e(LogTag.connectException, exception.message.toString())
            return null


        }
        catch (exception: Exception) {
            stateResponse?.OnError("Sprawdź wpisane dane do połączenia")
            Log.e(LogTag.connectException, exception.message.toString())
            return null


        }



    }

}


