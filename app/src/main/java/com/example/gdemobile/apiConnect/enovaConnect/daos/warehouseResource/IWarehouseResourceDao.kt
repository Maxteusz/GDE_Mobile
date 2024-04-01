package com.example.gdemobile.apiConnect.enovaConnect.daos.warehouseResource

import com.example.gdemobile.models.WarehouseResource

interface IWarehouseResourceDao  {
    suspend fun getPrimaryInformation(idCargo : Int) : List<WarehouseResource>?
    suspend fun getExtendedInformation(idCargo : Int, idWarehouse : Int) : List<WarehouseResource>?
}