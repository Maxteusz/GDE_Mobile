package com.example.gdemobile.apiConnect.enovaConnect

import android.util.Log
import com.example.gdemobile.apiConnect.enovaConnect.methods.IConnectEnovaMethod
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.utils.LogTag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.await
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException

class ConnectService(
    val stateResponse: StateResponse?,
) {

    suspend fun <X : Any> makeConnection(connectionParameters: IConnectEnovaMethod): X? {
        try {
            stateResponse?.OnLoading()

            val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
            var result = quotesApi.getListData<X>(RequestDto(connectionParameters))
            if (result.code() == 200) {
                Log.i(LogTag.enovaApiNameMethod, connectionParameters.methodName)
                Log.i(LogTag.enovaApiMessage, result.body()!!.exceptionMessage)
                Log.i(LogTag.enovaApiIsExcpetion, result.body()!!.isException.toString())
                Log.i(LogTag.enovaApiIsEmpty, result.body()!!.isEmpty.toString())
                Log.i(LogTag.enovaApiResultInstance, result.body()!!.resultInstance.toString())

                if (result.body()?.isException == false) {
                    var result = result.body()?.resultInstance
                    stateResponse?.OnSucces(result)
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


