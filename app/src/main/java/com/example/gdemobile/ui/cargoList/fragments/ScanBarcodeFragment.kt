package com.example.gdemobile.ui.cargoList.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getMainExecutor
import com.example.gdemobile.databinding.FragmentScanBarcodeBinding
import com.example.gdemobile.ui.cargoList.CargoListView
import com.example.gdemobile.utils.Utils
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class ScanBarcodeFragment : Fragment() {

    private lateinit var binding : FragmentScanBarcodeBinding
     private lateinit var  sharedViewModel: CargoListView
     private var lockedScan : Boolean = false
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
        sharedViewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                requireActivity(),
                listOf(Manifest.permission.CAMERA).toTypedArray(), 3
            );
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
                            if (!barcodes.isNullOrEmpty() && !lockedScan) {
                                lockedScan = true
                                sharedViewModel.addCargo(barcodes.first().displayValue.toString())
                                                               // Utils.showToast(activity?.applicationContext!!, "Get Cargo ${sharedViewModel.cargos.value?.size.toString()}")
                                Thread.sleep(1500)
                            }
                            barcodes.clear()
                            lockedScan = false

                            imageProxy.close()
                          //  lockedScan = false
                        }
                })

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {

                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, imageAnalysis, preview
                    )
                } catch (exc: Exception) {
                }
            },
            getMainExecutor(requireContext())
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