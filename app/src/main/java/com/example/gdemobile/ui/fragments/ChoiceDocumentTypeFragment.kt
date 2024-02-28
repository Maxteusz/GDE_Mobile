package com.example.gdemobile.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gdemobile.databinding.FragmentChoiceDocumentTypeBinding


class ChoiceDocumentTypeFragment : Fragment() {

    lateinit var binding : FragmentChoiceDocumentTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoiceDocumentTypeBinding.inflate(layoutInflater)

        return binding.root
    }


}