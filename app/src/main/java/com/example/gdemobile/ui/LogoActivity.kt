package com.example.gdemobile.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gdemobile.R
import com.example.gdemobile.ui.cargoList.CargoListActivity


class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, CargoListActivity::class.java)
        startActivity(intent);
        this.finishActivity(1)


    }


}
