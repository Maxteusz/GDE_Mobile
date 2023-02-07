package com.example.gdemobile.ui.cargoList

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.ActivityCargoListBinding


class CargoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCargoListBinding
    private lateinit var  adapter : ContractorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ContractorAdapter(CargoListView.listContractor())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)

        binding.barcodeTextfield.setEndIconOnClickListener({

        })

        //Keep focus on barcodeEditText and hide keyboard
        binding.barcodeEdittext.requestFocusFromTouch()
        getWindow().setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )

        binding.barcodeEdittext.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                Log.i("Key", "Pressed")
                return@setOnKeyListener true
            }
            false

        }



       // pobierzKontrahentow()

    }



  /*  fun pobierzKontrahentow() {

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
    }*/
}