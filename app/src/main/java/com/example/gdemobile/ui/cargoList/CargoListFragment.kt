package com.example.gdemobile.ui.cargoList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.R
import com.example.gdemobile.databinding.ActivityCargoListBinding
import com.example.gdemobile.databinding.FragmentCargoListBinding


class CargoListFragment : Fragment() {

    companion object {
        fun newInstance() = CargoListFragment()
    }

    private lateinit var viewModel: CargoListView
    private lateinit var cargosAdapter: CargoAdapter
    private lateinit var binding: FragmentCargoListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCargoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CargoListView::class.java)
        viewModel.cargos.observe(this.viewLifecycleOwner, { cargos ->
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                cargosAdapter = CargoAdapter(cargos)
                it.adapter = cargosAdapter
            }
        })
        // TODO: Use the ViewModel
    }

}