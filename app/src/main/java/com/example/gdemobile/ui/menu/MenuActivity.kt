package com.example.gdemobile.ui.menu

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.config.Mode
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.databinding.ActivityMenuBinding
import com.example.gdemobile.ui.cargoList.CargoListActivity
import com.example.gdemobile.ui.cargoList.CargoListView

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    lateinit var  menuView : MenuView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater);
        setContentView(binding.root)

        menuView = MenuView()
        binding.issuingButton.setOnClickListener {
            Mode.mode = Mode.Modes.Issuing
            menuView.openActivity(applicationContext, CargoListActivity(), view = null)
        }
    }
}