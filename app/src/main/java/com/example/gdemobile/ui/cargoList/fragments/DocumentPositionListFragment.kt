package com.example.gdemobile.ui.cargoList.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.cargoList.adapters.DocumentPositionAdapter
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.utils.BroadcasReceiverIntentActions
import com.example.gdemobile.utils.CustomToast
import com.example.gdemobile.utils.ToastMessages
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DocumentPositionListFragment() : Fragment(), IStateResponse {

    private lateinit var documentPositionAdapter: DocumentPositionAdapter
    private lateinit var binding: FragmentDocumentpositionListBinding
    private lateinit var viewModel: DocumentPositionsViewModel
    private lateinit var deffered: Deferred<Cargo?>
    private  var blockScanninng = false;

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BroadcasReceiverIntentActions.ACTION_AMOUNT_CARGO_DIALOG_DISMISSED) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.stateResponse = this@DocumentPositionListFragment

                }
            }
        }
    }


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
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onResume() {
        super.onResume()
        view?.isFocusableInTouchMode = true;
        view?.requestFocus();
        viewModel.stateResponse = this
        viewModel.getDocumentPositions()


    }

    override fun onPause() {
        super.onPause()

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

            override fun afterTextChanged(s: Editable?) {
                viewLifecycleOwner.lifecycleScope.launch {
                    withContext(coroutineContext)
                    {

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
                viewModel.stateResponse = this@DocumentPositionListFragment

            }
        }
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

    private val fastAddingCargoSateResult = object : IStateResponse {
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
            context?.let { CustomToast.showToast(it, message, CustomToast.Type.Error) }
            viewModel.stateResponse = this
            blockScanninng = false
        }

        override fun OnSucces() {
                context?.let {
                    CustomToast.showToast(
                        it,
                        ToastMessages.correctCargoAdded,
                        CustomToast.Type.Information
                    )




            }

        }
    }


    private val addCargoSateResult = object : IStateResponse {
        override fun OnLoading() {        }

        override suspend fun OnError(message: String) {
            context?.let { CustomToast.showToast(it, message, CustomToast.Type.Error) }
            viewModel.stateResponse = this
            blockScanninng = false
        }

        override fun OnSucces() {
            viewModel.stateResponse = this
            var documentPosition = DocumentPosition()
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(coroutineContext) {
                    documentPosition.cargo = deffered.await()
                    documentPosition.let {


                    }
                }
                blockScanninng = false


            }

        }
    }

}











