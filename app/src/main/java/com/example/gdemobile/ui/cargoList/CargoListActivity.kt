package com.example.gdemobile.ui.cargoList

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.utils.Utils


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var view: CargoListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        view = CargoListView()
      //  binding.setLifecycleOwner(this)

        view = ViewModelProvider(this).get(CargoListView::class.java)
        view.getCargo()
        view.cargos.observe(this, { x ->
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = CargoAdapter(x)
            }
        })
        binding.cameraButton.setOnClickListener({
            view.openActivity(applicationContext, ScanBarcodeActivity(), it)
        })
    }

    var temp = ""
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        temp += event.getUnicodeChar().toChar()
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                Utils.showToast(applicationContext, temp)
                view.addCargo(temp);
                temp = "";
                Log.i("CargoAdapter2", binding.recyclerview.adapter?.itemCount.toString())

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