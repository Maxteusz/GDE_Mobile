package com.example.gdemobile.ui.cargoList.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentCargoListBinding
import com.example.gdemobile.databinding.FragmentContractorListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.CargoListView

class ContractorListFragment : Fragment(), StateResponse {

private lateinit var  binding : FragmentContractorListBinding
    private lateinit var viewModel: CargoListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContractorListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(CargoListView::class.java)
        viewModel.stateResponse = this
        viewModel.getContractors()
        return binding.root
    }

    override fun OnLoading() {
        binding.cicularIcon.visibility = View.VISIBLE
    }

    override fun OnError() {
        binding.cicularIcon.visibility = View.VISIBLE
    }

    override fun OnSucces() {
        binding.cicularIcon.visibility = View.VISIBLE
    }


}