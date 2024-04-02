package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.warehouseResource.WarehouseResourceDao
import com.example.gdemobile.models.WarehouseResource
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class WarehouseResourceViewModel() : ViewModel(),
    IViewModel {

    override var stateResponse: IStateResponse? = null


    private val _primaryWarehouseResources = MutableLiveData<List<WarehouseResource>>(emptyList())
    private val _extendedWarehouseResources = MutableLiveData<List<WarehouseResource>>(emptyList())
    val primaryWarehouseResources: LiveData<List<WarehouseResource>>
        get() = _primaryWarehouseResources

    val extendedWarehouseResources: LiveData<List<WarehouseResource>>
        get() = _extendedWarehouseResources

     fun getPrimaryWarehouseResourceInformation(idCargo : Int){
        viewModelScope.launch {
            _primaryWarehouseResources.postValue(
                WarehouseResourceDao(stateResponse).getPrimaryInformation(
                    idCargo
                )
            )
        }
    }
     fun getExtendedWarehouseResourceInformation(idCargo: Int, idWarehouse : Int) {
        viewModelScope.launch {
            _extendedWarehouseResources.postValue(
                WarehouseResourceDao(stateResponse).getExtendedInformation(
                    idCargo,
                    idWarehouse
                )
            )
        }
    }


}