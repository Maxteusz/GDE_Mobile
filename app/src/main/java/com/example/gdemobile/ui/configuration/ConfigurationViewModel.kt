package com.example.gdemobile.ui.configuration

import androidx.lifecycle.ViewModel
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentConfigurationBinding


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