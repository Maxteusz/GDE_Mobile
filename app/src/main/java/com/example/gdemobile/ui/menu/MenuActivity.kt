package com.example.gdemobile.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gdemobile.databinding.ActivityMenuBinding
import com.example.gdemobile.ui.cargoList.CargoListActivity
import com.example.gdemobile.ui.configuration.ConfigurationActivity


import com.example.gdemobile.utils.Utils


class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    var  menuView : MenuView = MenuView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater);
        setContentView(binding.root)
        Utils.getConfiguration(this)
        init()
    }

    fun init()
    {
        binding.receivingButton.setOnClickListener {
            menuView.openActivity(applicationContext, CargoListActivity(), it)
        }
        binding.issuingButton.setOnClickListener {
            menuView.openActivity(applicationContext, CargoListActivity(), it)
        }

        binding.confButton.setOnClickListener {
            menuView.openActivity(applicationContext, ConfigurationActivity(),null)
        }
    }
}