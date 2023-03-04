package com.example.gdemobile.ui.cargoList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdemobile.R

class ScanBarcodeFragment : Fragment() {

    companion object {
        fun newInstance() = ScanBarcodeFragment()
    }

    private lateinit var viewModel: ScanBarcodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_barcode, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScanBarcodeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}