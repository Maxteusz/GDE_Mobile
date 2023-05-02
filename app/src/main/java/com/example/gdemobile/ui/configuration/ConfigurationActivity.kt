package com.example.gdemobile.ui.configuration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.databinding.ActivityConfigurationBinding
import com.example.gdemobile.ui.cargoList.CargoListViewModel

class ConfigurationActivity : AppCompatActivity() {


    private lateinit var binding: ActivityConfigurationBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(getLayoutInflater());
        setContentView(binding.root)
        binding.setLifecycleOwner(this)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

    }
}