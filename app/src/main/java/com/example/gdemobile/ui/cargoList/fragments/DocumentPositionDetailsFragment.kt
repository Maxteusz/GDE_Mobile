package com.example.gdemobile.ui.cargoList.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdemobile.R
import com.example.gdemobile.databinding.FragmentDocumentPositionDetailsBinding
import com.example.gdemobile.databinding.FragmentDocumentpositionListBinding
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.utils.NamesSharedVariable


class DocumentPositionDetailsFragment : Fragment() {

    private lateinit var binding : FragmentDocumentPositionDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDocumentPositionDetailsBinding.inflate(layoutInflater);
        binding.documentPosition = arguments?.getSerializable(NamesSharedVariable.documentPosition) as DocumentPosition
        return binding.root
    }


}