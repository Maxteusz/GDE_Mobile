package com.example.gdemobile.config

import android.content.Context
import com.example.gdemobile.R
import java.io.Serializable

class Config {

    companion object : Serializable {
        var ip: String? = ""
        var port: String? = "5000"
        var usernameERP: String? = "Administrator"
        var passwordERP: String? = "12345"
        var fastAddingDocumentPosition: Boolean = false
        var insertAmountCargo : Boolean = false;

        fun saveConfiguration(context: Context) {

            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(context.getString(R.string.ip_conf), ip)
                putString(context.getString(R.string.port_conf), port)
                putString(context.getString(R.string.username_conf), usernameERP)
                putString(context.getString(R.string.password_conf), passwordERP)
                putBoolean(context.getString(R.string.fast_adding_documentpostion), fastAddingDocumentPosition)
                putBoolean(context.getString(R.string.insert_amount_cargo), insertAmountCargo)

                apply()
                commit()
            }
        }

        fun loadConfiguration(context: Context) {
            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            ip = sharedPref.getString(context.getString(R.string.ip_conf), "")
            port = sharedPref.getString(context.getString(R.string.port_conf), "")
            usernameERP = sharedPref.getString(context.getString(R.string.username_conf), "")
            passwordERP = sharedPref.getString(context.getString(R.string.password_conf), "")
            fastAddingDocumentPosition = sharedPref.getBoolean(context.getString(R.string.fast_adding_documentpostion), true)
            insertAmountCargo = sharedPref.getBoolean(context.getString(R.string.insert_amount_cargo), false)
        }


    }


}