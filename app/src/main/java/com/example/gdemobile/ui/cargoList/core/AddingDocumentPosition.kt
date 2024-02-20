package com.example.gdemobile.ui.cargoList.core

import android.content.Context
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.viewmodels.CargoViewModel
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast

class AddingDocumentPosition(
    private val sharedViewModel: SharedViewModel,
   private val documentPositionsViewModel: DocumentPositionsViewModel,
   private val context: Context
) {
    suspend fun addDocumentPosition(ean: String)
    {
            if (CargoViewModel().getCargo(ean).await() != null)
                documentPositionsViewModel.addDocumentPosition(DocumentPosition(), sharedViewModel.document.value!!)
            else
                CustomToast.showToast(context,"Nie znaleziono towaru", CustomToast.Type.Information)

    }
}

