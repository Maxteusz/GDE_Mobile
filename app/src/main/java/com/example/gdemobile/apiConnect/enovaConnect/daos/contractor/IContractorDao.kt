package com.example.gdemobile.apiConnect.enovaConnect.daos.contractor

import com.example.gdemobile.models.Contractor

interface IContractorDao {
   suspend fun getContractors() : List<Contractor>?
}