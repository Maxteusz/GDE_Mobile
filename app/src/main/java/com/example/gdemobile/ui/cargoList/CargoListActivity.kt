package com.example.gdemobile.ui.cargoList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding


class CargoListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCargoListBinding
    private lateinit var navController: NavController
    private lateinit var view: CargoListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)
        view = ViewModelProvider(this).get(CargoListView::class.java)
        binding.setLifecycleOwner(this)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // initViewModel()
        // initViewsMethods()
    }

    var readBarcode = ""

    @SuppressLint("SuspiciousIndentation")
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode != KeyEvent.KEYCODE_ENTER) {
            readBarcode += event.getUnicodeChar().toChar()
        }
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {

                view.addCargo(readBarcode);
                readBarcode = "";
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }


    /* fun initViewsMethods() {
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
     }*/

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