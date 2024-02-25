package com.example.gdemobile.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdemobile.apiConnect.enovaConnect.daos.contractor.ContractorDao
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.IStateResponse
import kotlinx.coroutines.launch

class ContractorViewModel(override var stateResponse: IStateResponse? = null) : ViewModel(), IViewModel {
    private var _contractors= MutableLiveData<List<Contractor>>(emptyList())
    val contractors: LiveData<List<Contractor>>
        get() = _contractors

    fun getContractors() {
        viewModelScope.launch {
            _contractors.postValue(ContractorDao(stateResponse).getContractors())
        }
    }
}