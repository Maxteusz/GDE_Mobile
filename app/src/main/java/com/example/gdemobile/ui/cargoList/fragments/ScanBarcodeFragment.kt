package com.example.gdemobile.ui.cargoList.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
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
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentScanBarcodeBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.models.Price
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import com.example.gdemobile.utils.NamesSharedVariable
import com.example.gdemobile.utils.ToastMessages
import com.google.firebase.FirebaseApp
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScanBarcodeFragment : Fragment(), StateResponse {


    private lateinit var binding: FragmentScanBarcodeBinding
    private lateinit var sharedViewModel: InssuingCargoListViewModel
    private var lockedScan: Boolean = false
    private var idDocument = ""
    private var cargo: Cargo? = null
    private lateinit var deffered: Deferred<Cargo?>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val data = arguments
        idDocument = data!!.getString(NamesSharedVariable.idDocument, "")
        binding = FragmentScanBarcodeBinding.inflate(layoutInflater)
        sharedViewModel =
            ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        sharedViewModel.stateResponse = this


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
                                sharedViewModel.stateResponse = this
                                deffered = viewLifecycleOwner.lifecycleScope.async {

                                    return@async sharedViewModel.getCargoInformationByEan(
                                        scannedCode
                                    )
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
        binding.loadinglayout.root.visibility = View.VISIBLE
        binding.unlockButton.isEnabled = false;

    }

    override suspend fun OnError(message: String) {
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
        binding.loadinglayout.root.visibility = View.GONE
        binding.unlockButton.isEnabled = true;
    }


    override fun OnSucces() {
        binding.loadinglayout.root.visibility = View.GONE
        sharedViewModel.isRequiredLoadData.value = true
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(coroutineContext) {
                cargo = deffered.await()
                if(Config.fastAddingDocumentPosition) {
                   sharedViewModel.stateResponse = addCargoSateResult
                        val price =
                            cargo?.prices?.first { it.name == Price.PriceNames.PRIMARY }?.bruttoPerAmount
                        sharedViewModel.addCargoOnDocument(
                            idDocument,
                            cargo?.id,
                            cargo?.mainUnit?.id,
                            1.0,
                            price
                        )


                }
                else {
                    binding.unlockButton.isEnabled = true;
                    val data = Bundle()
                    data.putString(NamesSharedVariable.idDocument, idDocument)
                    data.putSerializable(NamesSharedVariable.cargo, cargo)
                    view
                    findNavController().navigate(
                        R.id.action_scanBarcodeFragment_to_amountCargoDialog,
                        data
                    )
                }
            }
        }


    }


    val addCargoSateResult = object : StateResponse
    {
        override fun OnLoading() {

        }

        override suspend fun OnError(message: String) {
            context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
        }

        override fun OnSucces() {
            binding.unlockButton.isEnabled = true;
            context?.let {
                CustomToast.showToast(
                    it,
                    ToastMessages.correctCargoAdded,
                    CustomToast.Type.Information
                )
            }
        }

    }



}