package com.example.gdemobile.ui.cargoList.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.BaseServiceCargoViewModel
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DocumentDetailsFragment : Fragment(), IStateResponse {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var viewModel: BaseServiceCargoViewModel
    private var document: Document = Document()

    private lateinit var defferedCreateDocument: Deferred<Document?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentDetailsBinding.inflate(inflater, container, false)




        //Section Document Definition
        binding.dokdefTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }

        //Section Contractor
        binding.contractorEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }




        
        return binding.root
    }

    override fun OnLoading() {
        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE
        viewModel.document.value = Document()
    }

    override suspend fun OnError(message: String) {
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        context?.let { CustomToast.showToast(it,message,CustomToast.Type.Error) }
    }

    override fun OnSucces() {
        binding.loadinglayout.visibility = View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(coroutineContext) {
                document = defferedCreateDocument.await()!!
                viewModel.isRequiredLoadData.value = false;
                viewModel.document.value = document
                viewModel.isRequiredLoadData.value = false
                findNavController().navigate(R.id.action_documentDetailsFragment_to_cargoListFragment)
            }
        }
    }


}