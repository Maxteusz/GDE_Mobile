package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gdemobile.apiConnect.enovaConnect.daos.warehouseResource.WarehouseResourceDao
import com.example.gdemobile.models.WarehouseResource
import com.example.gdemobile.ui.IStateResponse

class WarehouseResourceViewModel() : ViewModel(),
    IViewModel {

    override var stateResponse: IStateResponse? = null


    private val _primaryWarehouseResources = MutableLiveData<List<WarehouseResource>>(emptyList())
    private val _extendedWarehouseResources = MutableLiveData<List<WarehouseResource>>(emptyList())
    val primaryWarehouseResources: LiveData<List<WarehouseResource>>
        get() = _primaryWarehouseResources

    val extendedWarehouseResources: LiveData<List<WarehouseResource>>
        get() = _extendedWarehouseResources

    suspend fun getPrimaryWarehouseResourceInformation(idCargo : Int){
        return _primaryWarehouseResources.postValue(WarehouseResourceDao(stateResponse).getPrimaryInformation(idCargo))
    }
    suspend fun getExtendedWarehouseResourceInformation(idCargo: Int, idWarehouse : Int) {
        return _extendedWarehouseResources.postValue(WarehouseResourceDao(stateResponse).getExtendedInformation(idCargo, idWarehouse))
    }


}