package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.Document
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.InssuingCargoListViewModel
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.NamesSharedVariable
import com.example.gdemobile.utils.NamesSharedVariable.idDocument
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DocumentPositionListFragment() : Fragment(), IStateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private lateinit var viewModel: InssuingCargoListViewModel
    private lateinit var deffered: Deferred<Cargo?>


    private val listener =
        object : DocumentPositionAdapter.DeleteCargoViewHolderListener {
            override fun onDeleteDocumentPositionItemClicked(idDocumentPostion: Int) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.deleteCagoFromDocument(idDocumentPostion)
                    viewModel.refreshData()
                }
            }
        }

    private val listenerDocumentPostionDetails =
        object : DocumentPositionAdapter.DetailCargoViewHolderListener {
            override fun onOpenDetailDocumentPostion(documentPosition: DocumentPosition) {
                val data = Bundle()
                data.putSerializable(NamesSharedVariable.documentPosition, documentPosition)
                findNavController().navigate(
                    R.id.action_cargoListFragment_to_documentPositionDetailsFragment,
                    data
                )
            }
        }

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


    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true;
        view?.requestFocus();
        Log.i("Resume", "Resume")
        viewModel.stateResponse = this
        viewLifecycleOwner.lifecycleScope.launch {
           // if (viewModel.isRequiredLoadData.value == true)
                viewModel.refreshData()
        }
        view?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                viewModel.stateResponse = addCargoSateResult
                deffered = viewLifecycleOwner.lifecycleScope.async {
                    return@async viewModel.getCargoInformationByEan("123")
                }
                true
            }
            false
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_configmDocumentDialog)
        }
        binding.cameraButton.setOnClickListener {
            val data = Bundle()
            data.putString(idDocument, viewModel.document.value?.id)
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
                        viewModel.document.value?.documentPositions =
                            viewModel.filtrDocumentPosition(s.toString())!!
                        viewModel.document.value?.let { viewModel.updateDocument(it) }
                    }
                }

            }
        })
        binding.searchTextlayout.setEndIconOnClickListener {
            binding.searchTextfield.setText("")
            binding.searchTextfield.clearFocus()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.refreshData()
            }
        }
        viewModel.document.observe(viewLifecycleOwner, {
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                documentPositionAdapter = DocumentPositionAdapter(
                    viewModel.document.value?.documentPositions?.toMutableList()!!,
                    listener,
                    listenerDocumentPostionDetails
                )
                binding.cargosRecyclerview.adapter = documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        })


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

    private val fastAddingCargoSateResult = object : IStateResponse {
        override fun OnLoading() {

        }

        override suspend fun OnError(message: String) {
            context?.let { CustomToast.showToast(it, message, CustomToast.Type.Error) }
            viewModel.stateResponse = this
        }

        override fun OnSucces() {
            viewModel.stateResponse = this

        }

    }


    private val addCargoSateResult = object : IStateResponse {
        override fun OnLoading() {

        }

        override suspend fun OnError(message: String) {
            context?.let { CustomToast.showToast(it, message, CustomToast.Type.Error) }
            viewModel.stateResponse = this
        }

        override fun OnSucces() {
            viewModel.stateResponse = this
            var documentPosition = DocumentPosition()
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(coroutineContext) {
                    documentPosition.cargo = deffered.await()
                    documentPosition.let {
                        viewModel.addCargoOnDocument(
                            this@DocumentPositionListFragment,
                            it,
                            viewModel.document.value?.id!!,
                            fastAddingCargoSateResult

                        )
                    }
                }


            }

        }
    }

}











