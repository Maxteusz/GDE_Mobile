package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.cargo.CargoDao
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class CargoViewModel : ViewModel(), IViewModel {

    override var stateResponse: IStateResponse? = null
    fun getCargo(ean : String) : Deferred<Cargo?>
    {
        return viewModelScope.async {
            CargoDao(stateResponse).getCargoInformationByEan(ean)}

    }



}