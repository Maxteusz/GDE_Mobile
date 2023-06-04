package com.example.gdemobile.config

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.R
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.ui.StateResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable
import java.net.ConnectException
import java.net.SocketTimeoutException

class Config() {

    companion object : Serializable {
        var tokenApi: String? = null;
        var ip: String? = "192.168.1.168"
        var port: String? = "2001"
        var usernameERP: String? = "Administrator"
        var passwordERP: String? = "12345"
        var aggregation: Boolean = false
        var insertAmountCargo : Boolean = false;

        fun saveConfiguration(activity: Activity) {
            val sharedPref = activity?.getSharedPreferences(
                activity.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(activity.getString(R.string.ip_conf), ip)
                putString(activity.getString(R.string.port_conf), port)
                putString(activity.getString(R.string.username_conf), usernameERP)
                putString(activity.getString(R.string.password_conf), passwordERP)
                putBoolean(activity.getString(R.string.aggreagtion), aggregation)
                putBoolean(activity.getString(R.string.insert_amount_cargo), insertAmountCargo)

                apply()
                commit()
            }
        }

        fun loadConfiguration(activity: Activity) {
            val sharedPref = activity?.getSharedPreferences(
                activity.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            ip = sharedPref.getString(activity.getString(R.string.ip_conf), "")
            port = sharedPref.getString(activity.getString(R.string.port_conf), "")
            usernameERP = sharedPref.getString(activity.getString(R.string.username_conf), "")
            passwordERP = sharedPref.getString(activity.getString(R.string.password_conf), "")
            aggregation = sharedPref.getBoolean(activity.getString(R.string.aggreagtion), true)
            insertAmountCargo = sharedPref.getBoolean(activity.getString(R.string.insert_amount_cargo), false)
        }


    }


}