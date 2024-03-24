package com.example.gdemobile.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.gdemobile.ui.adapters.DocumentPositionAdapter
import com.example.gdemobile.ui.dialogs.AmountCargoDialog
import com.example.gdemobile.ui.dialogs.IDialogDismissListener
import com.example.gdemobile.ui.viewmodels.CargoViewModel
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast
import kotlinx.coroutines.launch


class DocumentPositionListFragment() : Fragment(), IStateResponse {

    private lateinit var _documentPositionAdapter: DocumentPositionAdapter
    private lateinit var _binding: FragmentDocumentpositionListBinding
    private lateinit var _viewModel: DocumentPositionsViewModel
    private var _scannedBarcode: String = ""


    private val sharedViewModel: SharedViewModel by activityViewModels()


    private val listener =
        object : DocumentPositionAdapter.DeleteCargoViewHolderListener {
            override fun onDeleteDocumentPositionItemClicked(idDocumentPostion: Int) {
                viewLifecycleOwner.lifecycleScope.launch {
                }
            }
        }

    private val listenerDocumentPositionDetails =
        object : DocumentPositionAdapter.DetailCargoViewHolderListener {
            override fun onOpenDetailDocumentPosition(documentPosition: DocumentPosition) {
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentpositionListBinding.inflate(layoutInflater);
        _binding.lifecycleOwner = this
        _viewModel =
            ViewModelProvider(requireActivity()).get(DocumentPositionsViewModel::class.java)
        _viewModel.stateResponse = this
        setColorsProgressSwipeLayout()
        initObservers()
        return _binding.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        initKeyListener()
        view?.isFocusableInTouchMode = true;
        view?.requestFocus()
    }

    @SuppressLint("ResourceType")
    fun setColorsProgressSwipeLayout() {
        _binding.swipeRefreshLayout.setColorSchemeColors(resources.getInteger(R.color.orange))
        _binding.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(resources.getInteger(R.color.darkGray))
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_cargoListFragment_to_configmDocumentDialog)
        }
        _binding.cameraButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_cargoListFragment_to_scanBarcodeFragment,
            )
        }
        _binding.cargosRecyclerview.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var sensitiveOnHideButtons = 10
                if (dy > sensitiveOnHideButtons) {
                    _binding.cameraButton.hide()
                    _binding.nextButton.hide()
                }
                if (dy < -sensitiveOnHideButtons) {
                    _binding.cameraButton.show()
                    _binding.nextButton.show()
                }

            }
        })
        _binding.searchTextfield.addTextChangedListener(object : TextWatcher {
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
        _binding.searchTextlayout.setEndIconOnClickListener {
            _binding.searchTextfield.setText("")
            _binding.searchTextfield.clearFocus()
        }
        _binding.swipeRefreshLayout.setOnRefreshListener {
            _viewModel.getDocumentPositions(sharedViewModel.document.value!!)
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    @SuppressLint("SuspiciousIndentation")
    private fun initObservers() {
        _viewModel.documentPositions.observe(viewLifecycleOwner) {
            _binding.cargosRecyclerview.also {
                it.layoutManager = LinearLayoutManager(context)
                it.setHasFixedSize(true)
                _documentPositionAdapter = DocumentPositionAdapter(
                    _viewModel.documentPositions.value?.toMutableList()!!,
                    listener,
                    listenerDocumentPositionDetails
                )
                _binding.cargosRecyclerview.adapter = _documentPositionAdapter
                (it.layoutManager as LinearLayoutManager).scrollToPosition(_binding.cargosRecyclerview.size)
            }
        }
        sharedViewModel.document.observe(viewLifecycleOwner) {
            _viewModel.getDocumentPositions(sharedViewModel.document.value!!)

        }


    }

    private fun initKeyListener() {
        view?.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN)
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        CargoViewModel(sharedViewModel, requireActivity())
                            .getCargo(_scannedBarcode.trim()) {
                                //findNavController().navigate(R.id.action_cargoListFragment_to_amountCargoDialog)
                                openDialog()
                            }

                    }
                    CustomToast.showToast(
                        requireActivity(),
                        _scannedBarcode,
                        CustomToast.Type.Information
                    )
                    _scannedBarcode = ""
                } else {
                    _scannedBarcode += event.unicodeChar.toChar()

                }

            false
        }

    }


    override fun OnLoading() {
        _binding.succeslayout.visibility = View.GONE
        _binding.errorlayout.visibility = View.GONE
        _binding.loadinglayout.root.visibility = View.VISIBLE
        _binding.swipeRefreshLayout.isRefreshing = false
        _binding.cargosRecyclerview.adapter = null

    }

    override suspend fun OnError(message: String) {
        _binding.errorlayout.visibility = View.VISIBLE
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.succeslayout.visibility = View.GONE
        _binding.swipeRefreshLayout.isRefreshing = false
    }


    override fun OnSucces() {
        _binding.errorlayout.visibility = View.GONE
        _binding.succeslayout.visibility = View.VISIBLE
        _binding.loadinglayout.root.visibility = View.GONE
        _binding.swipeRefreshLayout.isRefreshing = false
    }

    fun openDialog() {
        val dialog = AmountCargoDialog()
        dialog.dismissListener = object : IDialogDismissListener {
            override fun DismissDialogFunction() {
                dialog.dismiss()
                _viewModel.stateResponse = this@DocumentPositionListFragment
                _viewModel.getDocumentPositions(sharedViewModel.document.value!!)


            }
        }
        dialog.show(childFragmentManager, "AMOUNT_CARGO_DIALOG")


    }


}











