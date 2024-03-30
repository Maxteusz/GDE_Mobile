package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.gdemobile.models.Cargo
import com.example.gdemobile.ui.IStateResponse

class WarehouseResourceViewModel(override var stateResponse: IStateResponse?)
    : ViewModel(), IViewModel {

        fun getPrimaryWarehouseResourceInformation(cargo : Cargo)
        {

        }
}