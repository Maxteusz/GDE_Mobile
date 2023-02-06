package com.example.gdemobile.ui.cargoList

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.gdemobile.R
import com.example.gdemobile.RetrofitClient
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.models.Contractor


class CargoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCargoListBinding
    private lateinit var  adapter : ContractorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCargoListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        adapter = ContractorAdapter(listContractor())
        binding.recyclerview.adapter = adapter
        binding.recyclerview.setHasFixedSize(true)




       // pobierzKontrahentow()

    }

fun listContractor() : List<Contractor>
{
    var list = listOf<Contractor>(
        Contractor("ffdfd"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),
        Contractor("vcvbb"),



    )
    return list ;
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