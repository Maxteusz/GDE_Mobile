package com.example.gdemobile.ui.cargoList.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentCargoListBinding
import com.example.gdemobile.ui.cargoList.CargoAdapter
import com.example.gdemobile.ui.cargoList.CargoListView

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
        viewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        viewModel.cargos.observe(viewLifecycleOwner, Observer {cargos ->
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                cargoAdapter = CargoAdapter(viewModel.cargos.value!!)
                binding.cargosRecyclerview.adapter = cargoAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        } )
        binding.cameraButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_documentDetailsFragment)

        }


    }


}