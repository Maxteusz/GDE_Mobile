package com.example.gdemobile.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.ActivityMainBinding
import com.example.gdemobile.ui.dialogs.customdialog.CustomDialog
import com.example.gdemobile.ui.dialogs.customdialog.states.FinishAppDialogState
import com.example.gdemobile.ui.interfaces.IOnBackPressedListener
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.google.firebase.FirebaseApp


class MainActivitiy : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val _sharedViewModel : SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        binding.lifecycleOwner = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    if(navController.currentDestination?.id == R.id.menuFragment)
                    CustomDialog(FinishAppDialogState()).show(supportFragmentManager,"FINISH_APP_DIALOG")
                    else if (navController.currentDestination?.id == R.id.documentPostionListFragment) {
                        val fragment = navController.currentBackStackEntry?.destination
                        if (fragment is IOnBackPressedListener)
                            fragment.customOnBackPressed()
                    }
                    else
                    navController.popBackStack();
                }
            })

        }
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onResume() {
        super.onResume()
        Config.loadConfiguration(this)
    }



}