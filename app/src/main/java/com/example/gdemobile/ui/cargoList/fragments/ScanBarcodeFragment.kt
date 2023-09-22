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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentScanBarcodeBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.example.gdemobile.utils.NamesSharedVariable
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.coroutines.launch

class ScanBarcodeFragment : Fragment(), StateResponse {

     var ID_CARGO = "65336878-70cf-4e64-bd72-b742cd26a657"
    private lateinit var binding: FragmentScanBarcodeBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    private var lockedScan: Boolean = false
    private var idDocument = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val data = arguments
         idDocument = data!!.getString(NamesSharedVariable.idDocument, "")
        binding = FragmentScanBarcodeBinding.inflate(layoutInflater);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        FirebaseApp.initializeApp(requireActivity())
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = this

        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED
        )
            ActivityCompat.requestPermissions(
                requireActivity(),
                listOf(Manifest.permission.CAMERA).toTypedArray(), 3
            );
        binding.unlockButton.setOnClickListener {
            if (lockedScan)
                unlockScanning()
            else
                lockScanning()
        }
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


                imageAnalysis.setAnalyzer(getMainExecutor(requireActivity()), { imageProxy ->
                    val mediaImage = imageProxy.toBitmap()
                    val image = FirebaseVisionImage.fromBitmap(mediaImage)
                    detector.detectInImage(image)
                        .addOnSuccessListener { barcodes ->
                            if (!barcodes.isNullOrEmpty() && !lockedScan) {
                                var scannedCode = barcodes.first().rawValue.toString()
                                viewLifecycleOwner.lifecycleScope.launch {
                                sharedViewModel.getCargoInformation(ID_CARGO)
                                }
                                lockScanning()
                            }
                        }
                    imageProxy.close()

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
            getMainExecutor(requireActivity())
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

    fun lockScanning() {
        binding.unlockButton.setBackgroundColor(resources.getColor(R.color.red))
        binding.unlockButton.setText("Zablokowane")
        lockedScan = true;

    }

    fun unlockScanning() {
        binding.unlockButton.setBackgroundColor(resources.getColor(R.color.green))
        binding.unlockButton.setText("Skanowanie")
        lockedScan = false;

    }

    override fun OnLoading() {
        binding.loadinglayout.root.visibility = View.VISIBLE
    }

    override fun OnError(message: String) {
        this.showToast(message)
        binding.loadinglayout.root.visibility = View.GONE
    }

    override fun OnSucces() {
        binding.loadinglayout.root.visibility = View.GONE
        val data = Bundle()
        data.putString(NamesSharedVariable.idDocument, idDocument)
        findNavController().navigate(R.id.action_scanBarcodeFragment_to_amountCargoDialog,data)

    }

}