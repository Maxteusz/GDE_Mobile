package com.example.gdemobile.ui.menu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.config.Mode
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.databinding.ActivityMenuBinding
import com.example.gdemobile.ui.cargoList.CargoListActivity
import com.example.gdemobile.ui.cargoList.CargoListView
import kotlin.math.log

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    var  menuView : MenuView = MenuView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater);
        setContentView(binding.root)
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


    }
}