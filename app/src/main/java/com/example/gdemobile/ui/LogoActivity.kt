package com.example.gdemobile.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gdemobile.R
import com.example.gdemobile.ui.menu.MenuActivity


class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val intent = Intent(applicationContext, MenuActivity::class.java)
        startActivity(intent);
        this.finish()


    }


}
