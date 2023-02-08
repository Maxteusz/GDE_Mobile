package com.example.gdemobile.ui.cargoList

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityScanBarcodeBinding

import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class ScanBarcodeActivity : AppCompatActivity() {
  lateinit var  binding : ActivityScanBarcodeBinding
    private var qrScanIntegrator: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBarcodeBinding.inflate(layoutInflater);
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this,
                listOf( Manifest.permission.CAMERA ).toTypedArray(), 3);

        initQrScanIntegrator()




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
            } else {
                // If QRCode contains data.
                try {
                    // Converting the data to json format
                    Log.i("Scanned", result.contents.toString())
                    // Show values in UI.
                } catch (e: JSONException) {
                    e.printStackTrace()
                    // Data not in the expected format. So, whole object as toast message.
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    fun initQrScanIntegrator()
    {
        qrScanIntegrator = IntentIntegrator(this)
        qrScanIntegrator?.setOrientationLocked(false)
        qrScanIntegrator?.initiateScan()
    }


}