package com.example.gdemobile.ui.cargoList

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.utils.Utils
import kotlinx.coroutines.*


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var cargosAdapter: CargoAdapter
    private lateinit var view: CargoListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.setLifecycleOwner(this)
        initViewModel()
        initViewsMethods()
    }

    override fun onResume() {
        super.onResume()
        val intent = getIntent()
        val extras = intent.extras
        val barcode = extras?.getString("scannedBarcode","").toString()

        //if(!temp.toString().isNullOrEmpty())
        Log.i("Get temp", extras?.getString("scannedBarcode","").toString())
        view.addCargo(barcode)

    }


    var readBarcode = ""

    @SuppressLint("SuspiciousIndentation")
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode != KeyEvent.KEYCODE_ENTER) {
            readBarcode += event.getUnicodeChar().toChar()
        }
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Utils.showToast(applicationContext, readBarcode)
                view.addCargo(readBarcode);
                readBarcode = "";
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }
    fun clearFocus() {
        binding.textField.clearFocus()
        binding.searchEditText.clearFocus()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    fun initViewModel() {
        view = ViewModelProvider(this).get(CargoListView::class.java)
        view.cargos.observe(this, { cargos ->
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                cargosAdapter = CargoAdapter(cargos)
                it.adapter = cargosAdapter
            }
        })
        view.clearedFocus.observe(this, {x ->
            //if(x)
            //clearFocus()
        })
    }
    fun initViewsMethods() {
        binding.cameraButton.setOnClickListener({
            Utils.openActivity(applicationContext, ScanBarcodeActivity())
        })
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                view.resetCount()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                view.resetCount()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                view.resetCount()
                cargosAdapter.filtrElements(s.toString())
            }
        })
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