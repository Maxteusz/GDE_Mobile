package com.example.gdemobile.ui.cargoList

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.impl.CaptureConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gdemobile.databinding.ActivityScanBarcodeBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlin.concurrent.thread


class ScanBarcodeActivity : AppCompatActivity() {
    lateinit var binding: ActivityScanBarcodeBinding
    private var lockedScanning : Boolean = false;

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBarcodeBinding.inflate(layoutInflater);
        setContentView(binding.root)
        FirebaseApp.initializeApp(applicationContext);
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                this,
                listOf(Manifest.permission.CAMERA).toTypedArray(), 3
            );
        startCamera()
    }


    @SuppressLint("RestrictedApi")
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        val detector = FirebaseVision.getInstance()
            .visionBarcodeDetector

        cameraProviderFuture.addListener(
            {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                // Preview
                val preview = getPreviewCamera()
                val imageAnalysis = getImageAnalyzer()

                imageAnalysis.setAnalyzer(getMainExecutor(), { imageProxy ->
                    val mediaImage = imageProxy?.toBitmap()
                    val image = FirebaseVisionImage.fromBitmap(mediaImage!!)
                    detector.detectInImage(image)
                        .addOnSuccessListener { barcodes ->
                            if(!barcodes.isNullOrEmpty() && !lockedScanning) {
                                lockedScanning = true
                                CargoListActivity.view.addCargo(barcodes.first().displayValue.toString())
                                finishAndRemoveTask()
                            }
                            imageProxy.close()
                        }
                })
                // Select back camera as a default
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()
                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, imageAnalysis, preview
                    )

                } catch (exc: Exception) {
                    //Log.e(TAG, "Use case binding failed", exc)
                }


            },
            ContextCompat.getMainExecutor(this)
        )
    }

    @SuppressLint("RestrictedApi")
    private fun getPreviewCamera() : Preview {
        return Preview.Builder()
            .setDefaultCaptureConfig(CaptureConfig.defaultEmptyCaptureConfig())
            .build()
            .also {
                it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            }
    }
    private fun getImageAnalyzer() : ImageAnalysis  {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

}