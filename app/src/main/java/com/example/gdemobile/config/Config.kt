package com.example.gdemobile.config

import android.content.Context
import com.example.gdemobile.R
import java.io.Serializable

class Config {

    companion object : Serializable {
        var ip: String? = ""
        var port: String? = "5000"
        var database : String? = ""
        var usernameERP: String? = "Administrator"
        var passwordERP: String? = "12345"
        var fastAddingDocumentPosition: Boolean = false


        fun saveConfiguration(context: Context) {

            val sharedPref = context.getSharedPreferences(
                context.getString(R.string.configuration_file),
                Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(context.getString(R.string.ip_conf), ip?.trim())
                putString(context.getString(R.string.port_conf), port?.trim())
                putString(context.getString(R.string.database_conf), database?.trim())
                putString(context.getString(R.string.username_conf), usernameERP?.trim())
                putString(context.getString(R.string.password_conf), passwordERP?.trim())
                putBoolean(context.getString(R.string.fast_adding_documentpostion), fastAddingDocumentPosition)
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
            database = sharedPref.getString(context.getString(R.string.database_conf),"")
            usernameERP = sharedPref.getString(context.getString(R.string.username_conf), "")
            passwordERP = sharedPref.getString(context.getString(R.string.password_conf), "")
            fastAddingDocumentPosition = sharedPref.getBoolean(context.getString(R.string.fast_adding_documentpostion), true)

        }


    }


}