package com.example.gdemobile.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.gdemobile.apiConnect.enovaConnect.daos.cargo.CargoDao
import com.example.gdemobile.ui.IStateResponse
import com.example.gdemobile.utils.CustomToast

class CargoViewModel(
    private val sharedViewModel: SharedViewModel,
    private val context: Context,
    override var stateResponse: IStateResponse? = null)
    : ViewModel(), IViewModel {


    suspend fun getCargo(ean: String, action: () -> Unit) {
        val cargo = CargoDao(GetCargoStateResponse(context,action)).getCargoInformationByEan(ean)
        if(cargo != null)
            sharedViewModel.documentPosition.value?.cargo = cargo
    }
    private class GetCargoStateResponse(private val context: Context, private val action: () -> Unit) :
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