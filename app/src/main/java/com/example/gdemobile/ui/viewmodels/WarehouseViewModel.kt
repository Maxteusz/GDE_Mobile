package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.warehouse.WarehouseDao
import com.example.gdemobile.models.Warehouse
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class WarehouseViewModel(override var stateResponse: IStateResponse? = null) : ViewModel(),IViewModel{

    private var _warehouses= MutableLiveData<List<Warehouse>>(emptyList())
    val warehouses: LiveData<List<Warehouse>>
        get() = _warehouses
    fun getWarehouses() = viewModelScope.launch {
        _warehouses.postValue(WarehouseDao(stateResponse).getWarehouses())
    }



}