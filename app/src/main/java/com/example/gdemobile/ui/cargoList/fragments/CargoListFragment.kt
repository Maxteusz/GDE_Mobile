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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentCargoListBinding
import com.example.gdemobile.ui.cargoList.CargoListView
import com.example.gdemobile.ui.cargoList.adapters.CargoAdapter
import com.google.gson.JsonObject


class CargoListFragment : Fragment() {

    private lateinit var cargoAdapter: CargoAdapter
    private lateinit var binding: FragmentCargoListBinding

    companion object {
        fun newInstance() = CargoListFragment()
    }

    private lateinit var viewModel: CargoListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCargoListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        cargoAdapter = CargoAdapter(viewModel.cargos.value!!)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        viewModel.cargos.observe(viewLifecycleOwner, Observer { cargos ->
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                cargoAdapter = CargoAdapter(viewModel.cargos.value!!)
                binding.cargosRecyclerview.adapter = cargoAdapter
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
                cargoAdapter.filtrElements(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.searchTextlayout.setEndIconOnClickListener({
            binding.searchTextfield.text?.clear()

        })

        val jsonObject = JsonObject()
        jsonObject.addProperty("user", "Administrator")
        jsonObject.addProperty("password", "12345")
        viewModel.getToken()
    }


}