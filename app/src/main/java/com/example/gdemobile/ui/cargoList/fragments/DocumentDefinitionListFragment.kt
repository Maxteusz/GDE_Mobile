package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.gdemobile.databinding.FragmentDocumentDefinitionListBinding
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.DocumentDefinitionAdapter
import kotlinx.coroutines.launch


class DocumentDefinitionListFragment : Fragment(), IStateResponse {
    private lateinit var binding: FragmentDocumentDefinitionListBinding

    private lateinit var adapter: DocumentDefinitionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       /* binding = FragmentDocumentDefinitionListBinding.inflate(layoutInflater);
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this*/

        viewLifecycleOwner.lifecycleScope.launch {

        }
        return binding.root
    }

    val listener = object : DocumentDefinitionAdapter.ViewHolderListener {
        override fun onItemClicked(documentDefinition: DocumentDefinition) {


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*viewModel.documentDefinitions.observe(viewLifecycleOwner){
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
               adapter = DocumentDefinitionAdapter(viewModel.documentDefinitions.value!!.sortedBy { n -> n.symbol }, listener )
                binding.recyclerview.adapter = adapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        }*/
    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }
    override suspend fun OnError(message: String) {
        //binding.loadinglayout.visibility = View.VISIBLE
    }
    override fun  OnSucces() {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }

}