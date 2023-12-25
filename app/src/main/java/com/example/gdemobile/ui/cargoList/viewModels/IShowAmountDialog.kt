package com.example.gdemobile.ui.cargoList.viewModels

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.gdemobile.R
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.cargoList.fragments.DocumentPositionListFragment
import com.example.gdemobile.ui.cargoList.fragments.ScanBarcodeFragment
import com.example.gdemobile.utils.NamesSharedVariable

 interface IShowAmountDialog {
    @SuppressLint("RestrictedApi")
    fun showAmountDialog (fragment : Fragment, idDocument : String, documentPosition: DocumentPosition)
    {
        val data = Bundle()
        var action = 0

        when (fragment) {
            is ScanBarcodeFragment ->
                action = R.id.action_scanBarcodeFragment_to_amountCargoDialog
            is DocumentPositionListFragment -> {
                action = R.id.action_cargoListFragment_to_amountCargoDialog
            }
        }


        data.putString(NamesSharedVariable.idDocument, idDocument)
        data.putSerializable(NamesSharedVariable.cargo, documentPosition.cargo)

        fragment.findNavController().navigate(
            action,
            data
        )
    }

}