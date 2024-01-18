package com.example.gdemobile.ui

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.gdemobile.databinding.ActivityMainBinding
import com.example.gdemobile.ui.menu.MenuActivity


class LogoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = Intent(applicationContext, MenuActivity::class.java)
        animateFadeIn(binding.logoImage)
        animateFadeIn(binding.text)

        binding.logoImage.animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                startActivity(intent);
                this@LogoActivity.finish()
            }

        })

    }


    fun animateFadeIn(view : View) {
        val animation: Animation

        animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.fade_in
        )
        animation.duration = 2500
        view.startAnimation(animation)

    }


}
