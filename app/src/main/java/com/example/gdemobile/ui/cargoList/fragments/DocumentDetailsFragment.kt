package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentDetailsBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.BaseServiceCargoViewModel
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ExtensionFunction.Companion.showToast
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DocumentDetailsFragment : Fragment(), StateResponse {

    private lateinit var binding: FragmentDocumentDetailsBinding
    private lateinit var viewModel: BaseServiceCargoViewModel
    private var document: Document = Document()

    private lateinit var defferedCreateDocument: Deferred<Document?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.document.value = Document()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocumentDetailsBinding.inflate(layoutInflater)

        var document = viewModel.document.value
        viewModel.stateResponse = this

        viewModel.document.observe(viewLifecycleOwner, Observer
        {
            binding.document = document
        })


        //Section Document Definition
        binding.dokdefTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }
        binding.dokdefEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_documentDefinitionListFragment) }

        //Section Contractor
        binding.contractorEdittext.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }
        binding.contractorTextfield.setEndIconOnClickListener { findNavController().navigate(R.id.action_documentDetailsFragment_to_contractorListFragment) }

        binding.nextButton.setOnClickListener {
            defferedCreateDocument = viewLifecycleOwner.lifecycleScope.async {
                viewModel.document.value?.describe = binding.descibeTextfield.text.toString()
                document = viewModel.createNewDocument()
                return@async document

            }
        }


        return binding.root
    }

    override fun OnLoading() {

        binding.loadinglayout.visibility = View.VISIBLE
        binding.succeslayout.visibility = View.GONE


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
                val action =
                    DocumentDetailsFragmentDirections.actionDocumentDetailsFragmentToCargoListFragment(
                        document
                    )

                findNavController().navigate(action)
            }
        }
    }


}