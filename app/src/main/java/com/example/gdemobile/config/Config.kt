package com.example.gdemobile.config

import android.app.Activity
import android.content.Context
import com.example.gdemobile.R
import java.io.Serializable

class Config {

    companion object : Serializable {
        var tokenApi: String? = null;
        var ip: String? = "20.0.1.238"
        var port: String? = "5000"
        var usernameERP: String? = "Administrator"
        var passwordERP: String? = "12345"
        var fastAddingDocumentPosition: Boolean = false
        var insertAmountCargo : Boolean = false;

        fun saveConfiguration(activity: Activity) {
            val sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(activity.getString(R.string.ip_conf), ip)
                putString(activity.getString(R.string.port_conf), port)
                putString(activity.getString(R.string.username_conf), usernameERP)
                putString(activity.getString(R.string.password_conf), passwordERP)
                putBoolean(activity.getString(R.string.fast_adding_documentpostion), fastAddingDocumentPosition)
                putBoolean(activity.getString(R.string.insert_amount_cargo), insertAmountCargo)

                apply()
                commit()
            }
        }

        fun loadConfiguration(activity: Activity) {
            val sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            ip = sharedPref.getString(activity.getString(R.string.ip_conf), "")
            port = sharedPref.getString(activity.getString(R.string.port_conf), "")
            usernameERP = sharedPref.getString(activity.getString(R.string.username_conf), "")
            passwordERP = sharedPref.getString(activity.getString(R.string.password_conf), "")
            fastAddingDocumentPosition = sharedPref.getBoolean(activity.getString(R.string.fast_adding_documentpostion), true)
            insertAmountCargo = sharedPref.getBoolean(activity.getString(R.string.insert_amount_cargo), false)
        }


    }


}