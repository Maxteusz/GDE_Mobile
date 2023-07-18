package com.example.gdemobile.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gdemobile.RetrofitMethod
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.ui.StateResponse
import java.net.ConnectException
import java.net.SocketTimeoutException


class Utils {
    companion object {

        suspend fun getToken(stateResponse: StateResponse) {
            stateResponse.OnLoading()
            try {
            val quotesApi = RetrofitClient().getInstance().create(RetrofitMethod::class.java)
            val map: HashMap<String, String> =
                hashMapOf("login" to Config.usernameERP!!, "password" to Config.passwordERP!!)


                val result = quotesApi.getToken(map)
                if(result.code() == 200) {
                    Config.tokenApi = result.body().toString()
                    stateResponse.OnSucces()                }
                else
                    stateResponse.OnError()

            } catch (timeout: SocketTimeoutException) {
                Log.e(LogTag.timeoutException, timeout.message.toString())
                stateResponse.OnError()
            } catch (exception: ConnectException) {
                Log.e(LogTag.connectException, exception.message.toString())
                stateResponse.OnError()
            } catch (exception: Exception) {
                Log.e(LogTag.basicException, exception.message.toString())
                stateResponse.OnError()
            }


        }

    }
}