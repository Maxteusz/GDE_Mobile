package com.example.gdemobile.ui.menu


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.ActivityMenuBinding
import com.example.gdemobile.ui.cargoList.activities.CargoListActivity
import com.example.gdemobile.ui.configuration.ConfigurationActivity


class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    var  menuView : MenuView = MenuView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater);
        setContentView(binding.root)
        Config.loadConfiguration(this)
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