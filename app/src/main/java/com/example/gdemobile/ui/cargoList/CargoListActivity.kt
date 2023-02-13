package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.os.Bundle
import android.text.Editable
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
    private  var  view : CargoListView = CargoListView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ContractorAdapter(CargoListView.listContractor())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)


        binding.cameraButton.setOnClickListener({
            view.openActivity(applicationContext, ScanBarcodeActivity(), it)
        })

        var temp = "";
        binding.recyclerview.setOnKeyListener { _, keyCode, event ->
            if(event.action == KeyEvent.ACTION_UP) {
                temp += event.getUnicodeChar().toChar()
                Log.i("Chars", temp)
            }
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                Log.i("Key", "Pressed")
               /* binding.barcodeEdittext.requestFocusFromTouch()
                binding.barcodeEdittext.requestFocus()
                binding.barcodeTextfield.requestFocus()*/


                //binding.barcodeEdittext.setText("dsdsdsfff")

                return@setOnKeyListener true
            }
            false

        }

        //Keep focus on barcodeEditText and hide keyboard




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