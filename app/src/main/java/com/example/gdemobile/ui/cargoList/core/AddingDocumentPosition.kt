package com.example.gdemobile.ui.cargoList.core

import android.content.Context
import com.example.gdemobile.models.Currency
import com.example.gdemobile.models.DocumentPosition
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.CargoViewModel
import com.example.gdemobile.ui.viewmodels.DocumentPositionsViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast

class AddingDocumentPosition(
    private val sharedViewModel: SharedViewModel,
    private val context: Context
) {

    suspend fun addDocumentPosition(ean: String, action: () -> (Unit)) {
        if (CargoViewModel().getCargo(ean).await() == null)
            DocumentPositionsViewModel(GetCargoStadeResponse(context, action)).addDocumentPosition(
                getTestDocumentPosition()
            )


    }

    suspend fun getCargo(ean: String, action: () -> Unit) {
        var cargo = CargoViewModel(GetCargoStadeResponse(context, action)).getCargo(ean).await()
        if(cargo != null)
            sharedViewModel.documentPosition.value?.cargo = cargo
    }

    private fun getTestDocumentPosition(): DocumentPosition {
        return with(DocumentPosition())
        {
            valuePerUnit = Currency(2.0, "PLN")
            cargo?.EAN = "123"
            this
        }
    }

    private class GetCargoStadeResponse(private val context: Context, private val action: () -> Unit) :
        IStateResponse {
        override fun OnLoading() {}


        override suspend fun OnError(message: String) {
            CustomToast.showToast(context, message, CustomToast.Type.Information)
        }

        override fun OnSucces() {
            action()
        }
    }


}

