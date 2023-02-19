package com.example.gdemobile.ui.cargoList

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
        Log.i("Focus", this.currentFocus.toString())

        Toast.makeText(applicationContext, "dsdsd", Toast.LENGTH_SHORT).show()


        binding.cameraButton.setOnClickListener({
            view.openActivity(applicationContext, ScanBarcodeActivity(), it)
        })

        var temp = "";
        /*binding.recyclerview.setOnKeyListener { _, keyCode, event ->
            binding.linearLayout2.requestFocus()
            Log.i("FocusLog", this.currentFocus.toString())
            if(event.action == KeyEvent.ACTION_UP) {
                temp += event.getUnicodeChar().toChar()
                binding.barcodeEdittext.setText(temp)
               Toast.makeText(this, temp, Toast.LENGTH_SHORT)

            }
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                Log.i("Key", "Pressed")
               *//* binding.barcodeEdittext.requestFocusFromTouch()
                binding.barcodeEdittext.requestFocus()
                binding.barcodeTextfield.requestFocus()*//*


                binding.barcodeEdittext.setText("dsdsdsfff")

                return@setOnKeyListener true
            }
            false*/

        //}

        //Keep focus on barcodeEditText and hide keyboard





       // pobierzKontrahentow()

    }
var temp = ""
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
       // Toast.makeText(applicationContext, "dsdsd", Toast.LENGTH_SHORT)
        temp += event.getUnicodeChar().toChar()
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
               Toast.makeText(applicationContext, temp, Toast.LENGTH_SHORT).show()
                temp ="";
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
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