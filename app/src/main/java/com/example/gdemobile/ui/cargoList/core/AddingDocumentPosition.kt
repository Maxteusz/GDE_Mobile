package com.example.gdemobile.ui.cargoList.core

import android.content.Context
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.ui.viewmodels.CargoViewModel
import com.example.gdemobile.ui.viewmodels.SharedViewModel
import com.example.gdemobile.utils.CustomToast

class AddingDocumentPosition(
    private val sharedViewModel: SharedViewModel,
    private val context: Context
) {
    suspend fun getCargo(ean: String, action: () -> Unit) {
        var cargo = CargoViewModel(GetCargoStadeResponse(context, action)).getCargo(ean).await()
        if(cargo != null)
            sharedViewModel.documentPosition.value?.cargo = cargo
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

