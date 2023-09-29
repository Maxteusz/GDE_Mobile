package com.example.gdemobile.ui.cargoList.activities

import android.os.Bundle
import android.view.KeyEvent

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.fragments.DocumentPositionListFragment
import com.google.firebase.FirebaseApp


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var navController: NavController
    private lateinit var view: InssuingCargoListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        view = ViewModelProvider(this).get(InssuingCargoListViewModel::class.java)
        binding.setLifecycleOwner(this)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController



    }





}