package com.example.gdemobile.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.ui.StateResponse
import java.net.ConnectException
import java.net.SocketTimeoutException

class Utils {
    companion object {
        fun showToast(context: Context,message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        suspend fun getToken(stateResponse: StateResponse) {
            stateResponse.OnLoading()
            val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
            val map: HashMap<String, String> =
                hashMapOf("login" to Config.usernameERP, "password" to Config.passwordERP)
            Log.i("Json", map.toString())
            try {
                val result = quotesApi.getToken(map)
                if (result != null) {
                    Config.tokenApi = result.body().toString()
                    stateResponse.OnSucces()
                    Log.i("GetToken", result.body().toString())
                }
            } catch (timeout: SocketTimeoutException) {
                Log.e("SocketTimeoutException", timeout.message.toString())
                stateResponse.OnError()
            } catch (exception: ConnectException) {
                Log.e("ConnectException", exception.message.toString())
                stateResponse.OnError()
            }

        }



    }
}