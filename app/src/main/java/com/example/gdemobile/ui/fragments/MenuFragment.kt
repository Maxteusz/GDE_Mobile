package com.example.gdemobile.ui.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Acceptance
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.InventoryType
import com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes.Issuance
import com.example.gdemobile.config.UpdateApp
import com.example.gdemobile.databinding.FragmentMenuBinding
import com.example.gdemobile.ui.viewmodels.SharedViewModel


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater);
        initViews()
        return binding.root
    }

    fun initViews() {
        binding.confButton.setOnClickListener { findNavController().navigate(R.id.action_menuFragment_to_configurationFragment) }
        binding.receivingButton.setOnClickListener {
            sharedViewModel.setActionType(
                Acceptance()
            )
            findNavController().navigate(R.id.action_menuFragment_to_choiceDocumentTypeFragment)
        }
        binding.issuingButton.setOnClickListener {
            sharedViewModel.setActionType(Issuance())
            findNavController().navigate(R.id.action_menuFragment_to_choiceDocumentTypeFragment)
        }
        binding.inventoryButton.setOnClickListener {
            sharedViewModel.setActionType(InventoryType())
            findNavController().navigate(R.id.action_menuFragment_to_documentListFragment)
        }
        binding.logoImage.setOnClickListener {
            val updateManager = UpdateApp(requireActivity());
            updateManager.update()

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode ==  101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with the operation
            } else {
                Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}