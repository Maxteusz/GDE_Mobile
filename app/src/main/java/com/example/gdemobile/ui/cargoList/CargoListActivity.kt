package com.example.gdemobile.ui.cargoList

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.google.firebase.FirebaseApp


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var navController: NavController
    private lateinit var view: CargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        view = ViewModelProvider(this).get(CargoListViewModel::class.java)
        binding.setLifecycleOwner(this)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController



    }







}