package com.example.gdemobile.ui.cargoList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.databinding.FragmentCargoListBinding

class CargoListFragment : Fragment() {

    private lateinit var cargoAdapter : CargoAdapter
    private lateinit var binding : FragmentCargoListBinding

    companion object {
        fun newInstance() = CargoListFragment()
    }

    private lateinit var viewModel: CargoListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCargoListBinding.inflate(layoutInflater);
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CargoListView::class.java)
        viewModel.cargos.observe(viewLifecycleOwner, Observer {cargos ->
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                cargoAdapter = CargoAdapter(viewModel.cargos.value!!)
                binding.cargosRecyclerview.adapter = cargoAdapter
            }
        } )
        binding.cameraButton.setOnClickListener {
            findNavController().navigate(R.id.scanBarcodeFragment)
        }

    }


}