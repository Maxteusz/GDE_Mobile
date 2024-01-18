package com.example.gdemobile.ui.menu


import android.R
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
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
        binding.logoImage.setOnClickListener {
            animationRotate(it)
        }
        binding.receivingButton.setOnClickListener {
            menuView.openActivity(applicationContext, CargoListActivity(), it)
        }
        /*
        binding.issuingButton.setOnClickListener {
            menuView.openActivity(applicationContext, CargoListActivity(), it)
        }*/

        binding.confButton.setOnClickListener {
            menuView.openActivity(applicationContext, ConfigurationActivity(),null)
        }
    }
    fun animationRotate (view : View)
    {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 1000
        rotate.interpolator = LinearInterpolator()



        view.startAnimation(rotate)
    }
}