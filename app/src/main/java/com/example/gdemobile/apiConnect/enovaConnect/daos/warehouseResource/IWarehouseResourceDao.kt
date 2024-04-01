package com.example.gdemobile.apiConnect.enovaConnect.daos.warehouseResource

import com.example.gdemobile.models.WarehouseResource

interface IWarehouseResourceDao  {
    suspend fun getPrimaryInformation(barcode : String) : List<WarehouseResource>?
    suspend fun getExtendedInformation(barcode : String, IDWarehouse : Int) : List<WarehouseResource>?
}