package com.example.gdemobile.apiConnect.enovaConnect.daos.contractor

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.contractor.GetContractors
import com.example.gdemobile.models.Contractor
import com.example.gdemobile.ui.IStateResponse

class ContractorDao(stateResponse: IStateResponse?): Dao(stateResponse), IContractorDao {
    override suspend fun getContractors(): List<Contractor>? {
      return requestList<Contractor>(GetContractors())
    }

}