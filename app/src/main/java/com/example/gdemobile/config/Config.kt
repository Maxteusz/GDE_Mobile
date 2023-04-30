package com.example.gdemobile.config

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.ui.StateResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.SocketTimeoutException

class Config {

    companion object {
        var tokenApi = "";
        val ip: String = "192.168.1.168"
        val port: String = "2001"
        val usernameERP: String = "Administrator"
        val passwordERP: String = "12345"

    }


}