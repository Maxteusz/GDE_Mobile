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


    private val _warehouseResources = MutableLiveData<List<WarehouseResource>>(emptyList())
    val warehouseResources: LiveData<List<WarehouseResource>>
        get() = _warehouseResources

    suspend fun getPrimaryWarehouseResourceInformation(idCargo : Int){
        return _warehouseResources.postValue(WarehouseResourceDao(stateResponse).getPrimaryInformation(idCargo))
    }
    suspend fun getExtendedWarehouseResourceInformation(idCargo: Int, idWarehouse : Int): List<WarehouseResource>? {
        return WarehouseResourceDao(stateResponse).getExtendedInformation(idCargo, idWarehouse)
    }


}