package com.example.gdemobile.ui.cargoList.fragments

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.ui.viewmodels.CargoViewModel
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.launch


class DocumentPositionListFragment() : Fragment(), IStateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private lateinit var viewModel: DocumentPositionsViewModel



    private val sharedViewModel : SharedViewModel by activityViewModels()




    private val listener =
        object : DocumentPositionAdapter.DeleteCargoViewHolderListener {
            override fun onDeleteDocumentPositionItemClicked(idDocumentPostion: Int) {
                viewLifecycleOwner.lifecycleScope.launch {


                }
            }
        }

    private val listenerDocumentPostionDetails =
        object : DocumentPositionAdapter.DetailCargoViewHolderListener {
            override fun onOpenDetailDocumentPostion(documentPosition: DocumentPosition) {
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(requireActivity()).get(DocumentPositionsViewModel::class.java)
        viewModel.stateResponse = this
        initObservers()
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        initKeyListener()
        view?.isFocusableInTouchMode = true;
        view?.requestFocus()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_configmDocumentDialog)
        }
        binding.cameraButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_cargoListFragment_to_scanBarcodeFragment,
                )
        }
        binding.cargosRecyclerview.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var sensitiveOnHideButtons = 10
                if (dy > sensitiveOnHideButtons ) {
                    binding.cameraButton.hide()
                    binding.nextButton.hide()
                }
                if (dy < -sensitiveOnHideButtons ) {
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

            override fun afterTextChanged(s: Editable?) {}

        })
        binding.searchTextlayout.setEndIconOnClickListener {
            binding.searchTextfield.setText("")
            binding.searchTextfield.clearFocus()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
         viewModel.getDocumentPositions(sharedViewModel.document.value!!)
        }


    }
    private fun initObservers()
    {
        viewModel.documentPositions.observe(viewLifecycleOwner) {
            binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                documentPositionAdapter = DocumentPositionAdapter(
                    viewModel.documentPositions.value?.toMutableList()!!,
                    listener,
                    listenerDocumentPostionDetails
                )
                binding.cargosRecyclerview.adapter = documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(binding.cargosRecyclerview.size)
            }
        }
        sharedViewModel.document.observe(viewLifecycleOwner){
            viewModel.getDocumentPositions(sharedViewModel.document.value!!)
        }
    }

    private fun initKeyListener()
    {
        view?.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        CargoViewModel(sharedViewModel,requireActivity())
                            .getCargo("123",{
                                findNavController().navigate(R.id.action_cargoListFragment_to_amountCargoDialog)
                            })
                    }


            }

            false
        }

    }
    override fun OnLoading() {
        binding.succeslayout.visibility = View.GONE
        binding.errorlayout.visibility = View.GONE
        binding.loadinglayout.root.visibility = View.VISIBLE
        binding.swipeRefreshLayout.isRefreshing = false
        binding.cargosRecyclerview.adapter = null

    }

    override suspend fun OnError(message: String) {
        binding.errorlayout.visibility = View.VISIBLE
        binding.loadinglayout.root.visibility = View.GONE
        binding.succeslayout.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun OnSucces() {
        binding.errorlayout.visibility = View.GONE
        binding.succeslayout.visibility = View.VISIBLE
        binding.loadinglayout.root.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }






}











