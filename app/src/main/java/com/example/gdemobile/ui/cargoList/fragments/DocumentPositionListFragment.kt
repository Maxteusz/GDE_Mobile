package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.config.Config
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.CargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DocumentPositionListFragment : Fragment(), StateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private val thisFragment = this


    init {
       /* lifecycleScope.launch(Dispatchers.Main) {
            Utils.getToken(thisFragment)
        }*/
    }


    private lateinit var viewModel: CargoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(CargoListViewModel::class.java)
        documentPositionAdapter = DocumentPositionAdapter(viewModel.scannedCargo.value!!)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.scannedCargo.observe(viewLifecycleOwner, Observer {
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                val documentPositions = viewModel.scannedCargo.value!!
                documentPositionAdapter = DocumentPositionAdapter(viewModel.scannedCargo.value!!)
                binding.cargosRecyclerview.adapter = documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        })
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
                documentPositionAdapter.filtrElements(s.toString())
                if (s?.contains("<Agata>")!!)
                    binding.searchTextfield.setText(s.toString().replace("<Agata>", "\uD83D\uDE43"))
               /* if(s?.contains("<Ikea>")!!)
                {
                    val gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.")
                    startActivity(mapIntent)
                }*/
            }


            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.searchTextlayout.setEndIconOnClickListener({
            binding.searchTextfield.text?.clear()
        })
        binding.refreshButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                Utils.getToken(thisFragment)
            }
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


}