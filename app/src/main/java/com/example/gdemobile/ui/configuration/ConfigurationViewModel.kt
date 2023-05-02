package com.example.gdemobile.ui.configuration

import android.app.Activity
import android.content.Context
import android.provider.Settings.Global.getString

import androidx.lifecycle.ViewModel
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentConfigurationBinding

class ConfigurationViewModel : ViewModel() {

    lateinit var binding : FragmentConfigurationBinding
    fun saveConfiguration(activity : Activity)
    {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(activity.getString(R.string.ip_conf), binding.ipTextedit.text.toString())
            putString(activity.getString(R.string.port_conf), binding.portTextedit.text.toString())
            putString(activity.getString(R.string.username_conf), binding.usernameTextedit.text.toString())
            putString(activity.getString(R.string.password_conf), binding.passwordTextedit.text.toString())
            apply()
        }
    }
    fun setConfiguration()
    {
        Config.ip = binding.ipTextedit.text.toString()
        Config.port = binding.portTextedit.text.toString()
        Config.usernameERP = binding.usernameTextedit.text.toString()
        Config.passwordERP = binding.passwordTextedit.text.toString()
    }
}