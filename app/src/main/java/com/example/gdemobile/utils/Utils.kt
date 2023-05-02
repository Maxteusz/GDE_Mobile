package com.example.gdemobile.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.R
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.config.Config
import com.example.gdemobile.ui.StateResponse
import java.net.ConnectException
import java.net.SocketTimeoutException


class Utils {
    companion object {
        fun showToast(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
        suspend fun getToken(stateResponse: StateResponse) {
            stateResponse.OnLoading()
            try {
            val quotesApi = RetrofitClient().getInstance().create(ApiInterface::class.java)
            val map: HashMap<String, String> =
                hashMapOf("login" to Config.usernameERP!!, "password" to Config.passwordERP!!)
            Log.i("Json", map.toString())

                val result = quotesApi.getToken(map)
                if(result.code() == 200) {
                    Config.tokenApi = result.body().toString()
                    stateResponse.OnSucces()
                    Log.i("GetToken", result.body().toString())
                }
                else
                    stateResponse.OnError()

            } catch (timeout: SocketTimeoutException) {
                Log.e("SocketTimeoutException", timeout.message.toString())
                stateResponse.OnError()
            } catch (exception: ConnectException) {
                Log.e("ConnectException", exception.message.toString())
                stateResponse.OnError()
            } catch (exception: Exception) {
                Log.e("GetTokenException", exception.message.toString())
                stateResponse.OnError()
            }


        }
        fun getConfiguration (activity : Activity)
        {

            val sharedPref = activity?.getSharedPreferences("configuration",Context.MODE_PRIVATE) ?: return
            Config.ip = sharedPref.getString(activity.getString(R.string.ip_conf), "")
            Config.port = sharedPref.getString(activity.getString(R.string.port_conf), "")
            Config.usernameERP = sharedPref.getString(activity.getString(R.string.username_conf), "")
            Config.passwordERP = sharedPref.getString(activity.getString(R.string.password_conf), "")

            Log.i("fdfd", Config.ip.toString())


        }
    }
}