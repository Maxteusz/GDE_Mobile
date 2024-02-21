package com.example.gdemobile.ui.cargoList.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentScanBarcodeBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.core.AddingDocumentPosition
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.coroutines.launch

class ScanBarcodeFragment : Fragment(), IStateResponse {


    private lateinit var binding: FragmentScanBarcodeBinding

    private var lockedScan: Boolean = false

    private val sharedViewModel : SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBarcodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        FirebaseApp.initializeApp(requireActivity())



        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                requireActivity(),
                listOf(Manifest.permission.CAMERA).toTypedArray(), 3
            )
        binding.unlockButton.setOnClickListener {
            if (lockedScan)
                unlockScanning()
            else
                lockScanning()
        }
        startCamera()

    }


    private fun startCamera(): Cargo? {
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


                imageAnalysis.setAnalyzer(getMainExecutor(requireActivity())) { imageProxy ->
                    val mediaImage = imageProxy.toBitmap()
                    val image = FirebaseVisionImage.fromBitmap(mediaImage)
                    detector.detectInImage(image)
                        .addOnSuccessListener { barcodes ->
                            if (!barcodes.isNullOrEmpty() && !lockedScan) {
                                val scannedCode = barcodes.first().rawValue.toString()
                                viewLifecycleOwner.lifecycleScope.launch {
                                    AddingDocumentPosition(sharedViewModel,requireActivity()).getCargo(scannedCode, {
                                        findNavController().navigate(R.id.action_scanBarcodeFragment_to_amountCargoDialog)

                                    })
                                }
                                lockScanning()
                            }
                        }
                    imageProxy.close()
                }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, imageAnalysis, preview
                    )
                } catch (exc: Exception) {
                }
            },
            getMainExecutor(requireActivity())
        )
        return null
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

    fun lockScanning() {
        binding.unlockButton.setBackgroundColor(resources.getColor(R.color.red))
        binding.unlockButton.text = getString(R.string.blocked)
        lockedScan = true

    }

    fun unlockScanning() {
        binding.unlockButton.setBackgroundColor(resources.getColor(R.color.green))
        binding.unlockButton.text = getString(R.string.scanning)
        lockedScan = false

    }

    override fun OnLoading() {


    }

    override suspend fun OnError(message: String) {

    }


    override fun OnSucces() {
        }


    }




