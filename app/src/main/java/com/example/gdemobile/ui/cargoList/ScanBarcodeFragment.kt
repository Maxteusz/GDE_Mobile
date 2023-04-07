package com.example.gdemobile.ui.cargoList

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.impl.CaptureConfig
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getMainExecutor
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentCargoListBinding
import com.example.gdemobile.databinding.FragmentScanBarcodeBinding
import com.example.gdemobile.utils.Utils
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class ScanBarcodeFragment : Fragment() {

    private lateinit var binding : FragmentScanBarcodeBinding
    companion object {
        fun newInstance() = ScanBarcodeFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanBarcodeBinding.inflate(layoutInflater);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Utils.showToast(requireContext(),"Udało się")
        startCamera()
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val detector = FirebaseVision.getInstance()
            .visionBarcodeDetector
        cameraProviderFuture.addListener(
            {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                // Preview
                val preview = initPreviewCamera()
                val imageAnalysis = initImageAnalyzer()

                imageAnalysis.setAnalyzer(getMainExecutor(requireContext()), { imageProxy ->
                    val mediaImage = imageProxy?.toBitmap()
                    val image = FirebaseVisionImage.fromBitmap(mediaImage!!)
                    detector.detectInImage(image)
                        .addOnSuccessListener { barcodes ->
                            if (!barcodes.isNullOrEmpty()) {

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
            ContextCompat.getMainExecutor(requireContext())
        )
    }

    @SuppressLint("RestrictedApi")
    private fun initPreviewCamera(): Preview {
        return Preview.Builder()
            .setDefaultCaptureConfig(CaptureConfig.defaultEmptyCaptureConfig())
            .build()
            .also {
                it.setSurfaceProvider(binding.cameraPreview.surfaceProvider)
            }
    }

    private fun initImageAnalyzer(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

}