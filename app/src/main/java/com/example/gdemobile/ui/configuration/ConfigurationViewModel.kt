package com.example.gdemobile.ui.configuration

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.provider.Settings.Global.getString
import android.util.Log

import androidx.lifecycle.ViewModel
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentConfigurationBinding
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.models.Document
import com.google.gson.Gson

class ConfigurationViewModel : ViewModel() {

    lateinit var binding : FragmentConfigurationBinding

    fun setConfiguration()
    {
        Config.ip = binding.ipTextedit.text.toString()
        Config.port = binding.portTextedit.text.toString()
        Config.usernameERP = binding.usernameTextedit.text.toString()
        Config.passwordERP = binding.passwordTextedit.text.toString()
        Config.aggregation = binding.aggregationSwitch.isChecked
        Config.insertAmountCargo = binding.scanmodeSwitch.isChecked

    }

}