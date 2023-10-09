package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.utils.NamesSharedVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DocumentPositionListFragment() : Fragment(), StateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private val arg: DocumentPositionListFragmentArgs by navArgs()


    private lateinit var viewModel: InssuingCargoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val doc = arg.document

        binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.document.value = doc
        viewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                if (doc != null) {
                    if(!doc.isNew)
                    viewModel.getDocumentPositions(doc.id)
                    else
                        doc.isNew = false
                } else
                    OnError("Błąd pobrania dokumentu")
            }
        }
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
           findNavController().navigate(com.example.gdemobile.R.id.action_cargoListFragment_to_configmDocumentDialog)
        }
        binding.cameraButton.setOnClickListener {
            val data = Bundle()
            data.putString(NamesSharedVariable.idDocument, arg.document?.id)
            findNavController().navigate(
                com.example.gdemobile.R.id.action_cargoListFragment_to_scanBarcodeFragment,
                data
            )
        }
        binding.cargosRecyclerview.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 10) {
                    binding.cameraButton.hide()
                    binding.nextButton.hide()
                }
                if (dy < -10) {
                    binding.cameraButton.show()
                    binding.nextButton.show()
                }

            }
        })


        binding.searchTextfield.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.filtrDocumentPosition(s.toString())
            }
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

    override fun OnError(message: String) {
        binding.errorlayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
    }

    override fun <T> OnSucces(result: T?) {
        binding.errorlayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
    }
}


    /*@SuppressLint("SuspiciousIndentation")
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

     *//*   if (keyCode == KeyEvent.KEYCODE_ENTER) {
            if (Config.insertAmountCargo)
                findNavController().navigate(com.example.gdemobile.R.id.action_cargoListFragment_to_amountCargoDialog)
            else
                viewModel.addCargo(viewModel.scannedBarcode.value!!)


        } else
            viewModel.scannedBarcode.value += event?.unicodeChar?.toChar()
        return true
    }*//*
}*/




