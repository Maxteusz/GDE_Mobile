package com.example.gdemobile.ui.cargoList

import android.annotation.SuppressLint
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
import kotlin.time.Duration.Companion.milliseconds


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var  cargosAdapter : CargoAdapter
    private  var timeCount : Long = System.currentTimeMillis()
    companion object{
        var view: CargoListView =  CargoListView()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        binding.setLifecycleOwner(this)
        view = ViewModelProvider(this).get(CargoListView::class.java)
        clearFocus()

        view.getCargo()

        view.cargos.observe(this, { cargos ->
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                cargosAdapter = CargoAdapter(cargos)
                it.adapter = cargosAdapter
            }
        })
        binding.cameraButton.setOnClickListener({
            view.openActivity(applicationContext, ScanBarcodeActivity())
        })

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               cargosAdapter.filtrElements(s.toString())
                Log.i("CargosCount", view.cargos.value?.size.toString())
            }
        })
        binding.searchEditText.setOnFocusChangeListener { view, b ->
            if(!view.isFocused)
            timeCount = System.currentTimeMillis()

        }

    }

    var readBarcode = ""
    @SuppressLint("SuspiciousIndentation")
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if(keyCode != KeyEvent.KEYCODE_ENTER)
        {
            val currentTime = System.currentTimeMillis()
            Utils.showToast(applicationContext,currentTime.minus(timeCount).toString())
            Log.i("Click", currentTime.minus(timeCount).toString());
            if(currentTime.minus(timeCount) < 3000L) {
                binding.textField.clearFocus()
                binding.searchEditText.clearFocus()
                readBarcode += event.getUnicodeChar().toChar()
            }


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

    fun clearFocus()
    {
        binding.textField.clearFocus()
        binding.searchEditText.clearFocus()
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