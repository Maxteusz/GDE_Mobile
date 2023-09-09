package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.ui.cargoList.interfaces.KeyListener

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DocumentPositionListFragment : Fragment(), StateResponse, KeyListener {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private val thisFragment = this


    init {
        /*lifecycleScope.launch(Dispatchers.Main) {
            Utils.getToken(thisFragment)
        }*/
    }


    private lateinit var viewModel: InssuingCargoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.scannedCargo.observe(viewLifecycleOwner, {
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                documentPositionAdapter = DocumentPositionAdapter(
                    viewModel.scannedCargo.value!!.toMutableList(),
                    viewModel
                )

                binding.cargosRecyclerview.adapter = documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_documentDetailsFragment)
        }
        binding.cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_scanBarcodeFragment)
        }
        binding.searchTextfield.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filtrDocumentPosition(s.toString())
                /* if (s?.contains("")!!)
                        binding.searchTextfield.setText(s.toString().replace("", "\uD83D\uDE43"))*/
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.searchTextlayout.setEndIconOnClickListener({
            binding.searchTextfield.text?.clear()
        })
        binding.refreshButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {

            }
        }
        binding.searchTextlayout.setEndIconOnClickListener {
            binding.searchTextfield.setText("")
            binding.searchTextfield.clearFocus()
        }
    }

    override fun OnLoading() {
        binding.succeslayout.visibility = View.GONE
        binding.errorlayout.visibility = View.GONE
        binding.loadinglayout.visibility = View.VISIBLE
    }

    override fun OnError() {
        binding.errorlayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
    }

    override fun OnSucces() {
        binding.errorlayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (Config.insertAmountCargo)
                findNavController().navigate(R.id.action_cargoListFragment_to_amountCargoDialog)
            else
                viewModel.addCargo(viewModel.scannedBarcode.value!!)


        } else
            viewModel.scannedBarcode.value += event?.unicodeChar?.toChar()
        return true
    }
}


