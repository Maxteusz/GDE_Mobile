package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withStarted
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.FragmentDocumentDefinitionListBinding
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentDefinitionAdapter
import kotlinx.coroutines.launch


class DocumentDefinitionListFragment : Fragment(), StateResponse {
    private lateinit var binding: FragmentDocumentDefinitionListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    private lateinit var adapter: DocumentDefinitionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentDefinitionListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this

        viewLifecycleOwner.lifecycleScope.launch {
            withStarted {
                //if (viewModel.documentDefinitions.value?.isEmpty() == true)
                  //  viewModel.getDocumentDefinitions()

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.documentDefinitions.observe(viewLifecycleOwner, Observer {
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
               adapter = DocumentDefinitionAdapter(viewModel.documentDefinitions.value!!.sortedBy { n -> n.symbol }, viewModel, this)
                binding.recyclerview.adapter = adapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        })
    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }
    override fun OnError(message: String) {
        //binding.loadinglayout.visibility = View.VISIBLE
    }
    override fun <T> OnSucces(result: T?) {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }

}