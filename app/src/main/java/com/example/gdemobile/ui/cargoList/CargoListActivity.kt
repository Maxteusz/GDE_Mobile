package com.example.gdemobile.ui.cargoList

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.ApiInterface
import com.example.gdemobile.R
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.databinding.ActivityCargoListBinding


class CargoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCargoListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cargo_list)
      //  binding.
        pobierzKontrahentow()
    }



    fun pobierzKontrahentow() {

        var retrofit = RetrofitClient().getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenStarted {
            try {
                val response = apiInterface.getContractors();
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(
                        this@CargoListActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }
}