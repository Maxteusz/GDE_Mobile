package com.example.gdemobile.ui.cargoList

import android.os.Bundle
import android.view.KeyEvent

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.ui.cargoList.fragments.DocumentPositionListFragment
import com.google.firebase.FirebaseApp


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var navController: NavController
    private lateinit var view: InssuingCargoListViewModel
    private lateinit var currentFragment: Fragment
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


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val navHostFragment = supportFragmentManager.primaryNavigationFragment
        val fragment = navHostFragment!!.childFragmentManager.fragments[0]
        if(fragment is DocumentPositionListFragment)
            fragment.onKeyDown(keyCode, event)

        return super.onKeyDown(keyCode, event)
    }


}