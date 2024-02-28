package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdemobile.databinding.FragmentDocumentDefinitionListBinding
import com.example.gdemobile.models.DocumentDefinition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.adapters.DocumentDefinitionAdapter
import com.example.gdemobile.ui.viewmodels.DocumentDefinitionViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.launch


class DocumentDefinitionListFragment : Fragment(), IStateResponse {
    private lateinit var binding: FragmentDocumentDefinitionListBinding
    private lateinit var documentDefinitionViewModel: DocumentDefinitionViewModel
    private lateinit var adapter: DocumentDefinitionAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocumentDefinitionListBinding.inflate(layoutInflater);
        documentDefinitionViewModel =
            ViewModelProvider(requireActivity()).get(DocumentDefinitionViewModel::class.java)
        documentDefinitionViewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {

        }
        return binding.root
    }

    val onClickListener = object : DocumentDefinitionAdapter.ViewHolderListener {
        override fun onItemClicked(documentDefinition: DocumentDefinition) {
            sharedViewModel.document.value?.definition = documentDefinition
            findNavController().popBackStack()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        documentDefinitionViewModel.documentDefinitions.observe(viewLifecycleOwner) {
            binding.recyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                adapter = DocumentDefinitionAdapter(
                    documentDefinitionViewModel.documentDefinitions.value!!
                        .sortedBy { n -> n.symbol },
                    onClickListener
                )
                binding.recyclerview.adapter = adapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.recyclerview.size)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        documentDefinitionViewModel.getDocumentDefinitions()
    }

    override fun OnLoading() {
        binding.loadinglayout.root.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
    }

    override suspend fun OnError(message: String) {
        binding.loadinglayout.root.visibility = View.INVISIBLE
        CustomToast.showToast(requireActivity(), message, CustomToast.Type.Error)
    }

    override fun OnSucces() {
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
    }

}