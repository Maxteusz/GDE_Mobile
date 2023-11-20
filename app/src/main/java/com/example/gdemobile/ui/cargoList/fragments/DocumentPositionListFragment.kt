package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.StateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.utils.NamesSharedVariable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.jar.Attributes.Name


class DocumentPositionListFragment() : Fragment(), StateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private val arg: DocumentPositionListFragmentArgs by navArgs()
    private var document: Document? = null


    private lateinit var viewModel: InssuingCargoListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity()).get(InssuingCargoListViewModel::class.java)
        viewModel.stateResponse = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.nextButton.setOnClickListener {
            findNavController().navigate(com.example.gdemobile.R.id.action_cargoListFragment_to_configmDocumentDialog)
        }
        binding.cameraButton.setOnClickListener {
            val data = Bundle()
            data.putString(NamesSharedVariable.idDocument, arg.document?.id)
            findNavController().navigate(
                R.id.action_cargoListFragment_to_scanBarcodeFragment,
                data
            )
        }
        binding.cargosRecyclerview.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 10) {
                    binding.cameraButton.hide()
                    binding.nextButton.hide()
                }
                if (dy < -10) {
                    binding.cameraButton.show()
                    binding.nextButton.show()
                }

            }
        })

        binding.searchTextfield.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

            override fun afterTextChanged(s: Editable?) {
                viewLifecycleOwner.lifecycleScope.launch {
                    withContext(coroutineContext)
                    {
                        document?.documentPositions =
                            viewModel.filtrDocumentPosition(s.toString())!!
                        document?.let { viewModel.updateDocument(it) }
                    }
                }

            }
        })
        binding.searchTextlayout.setEndIconOnClickListener({
            binding.searchTextfield.text?.clear()
        })
        binding.refreshButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {

            }
        }
        binding.searchTextlayout.setEndIconOnClickListener {
            binding.searchTextfield.setText("")
            binding.searchTextfield.clearFocus()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                refreshData()
            }
        }
        document = arg.document

        val listener =
            object : DocumentPositionAdapter.DeleteCargoViewHolderListener {
            override fun onDeleteDocumentPositionItemClicked(idDocumentPostion: Int) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteCagoFromDocument(idDocumentPostion)
                    refreshData()
                }
            }
        }

        val listenerDocumentPostionDetails =
            object : DocumentPositionAdapter.DetailCargoViewHolderListener {
                override fun onOpenDetailDocumentPostion(documentPosition: DocumentPosition) {
                    val data = Bundle()
                    data.putSerializable(NamesSharedVariable.documentPosition,documentPosition)
                    findNavController().navigate(
                        R.id.action_cargoListFragment_to_documentPositionDetailsFragment,
                        data
                    )

                }

            }

        viewModel.document.observe(viewLifecycleOwner, {
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)

                it.setHasFixedSize(true)
                documentPositionAdapter = DocumentPositionAdapter(
                    document?.documentPositions?.toMutableList()!!,
                    listener,
                    listenerDocumentPostionDetails
                )
                binding.cargosRecyclerview.adapter = documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            whenStarted {
                if (!document?.isNew!!) {
                    document!!.documentPositions =
                        viewModel.getDocumentPositions(document!!.id)
                    viewModel.updateDocument(document!!)

                } else
                    document!!.isNew = false


            }
        }

    }

    suspend fun refreshData() {
        document!!.documentPositions =
            viewModel.getDocumentPositions(document!!.id)
        viewModel.updateDocument(document!!)
    }

    override fun OnLoading() {
        binding.succeslayout.visibility = View.GONE
        binding.errorlayout.visibility = View.GONE
        binding.loadinglayout.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override suspend fun OnError(message: String) {
        binding.errorlayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun OnSucces() {
        binding.errorlayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.loadinglayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }
}







