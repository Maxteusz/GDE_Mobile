package com.example.gdemobile.apiConnect.enovaConnect.daos.warehouseResource

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource.GetExtendedInformation
import com.example.gdemobile.apiConnect.enovaConnect.methods.warehouseResource.GetPrimaryInformation
import com.example.gdemobile.models.WarehouseResource
import com.example.gdemobile.ui.IStateResponse

class WarehouseResourceDao(stateResponse: IStateResponse?) : Dao(stateResponse), IWarehouseResourceDao{
    override suspend fun getPrimaryInformation(barcode: String): List<WarehouseResource>?{
        return requestList<WarehouseResource>(GetPrimaryInformation(barcode))
    }

    override suspend fun getExtendedInformation(
        barcode: String,
        IDWarehouse: Int
    ): List<WarehouseResource>? {
        return requestList<WarehouseResource>(GetExtendedInformation(barcode,IDWarehouse))
    }


}