package com.example.gdemobile.apiConnect.enovaConnect.daos.warehouse

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao
import com.example.gdemobile.apiConnect.enovaConnect.methods.warehouse.GetWarehouses
import com.example.gdemobile.models.Warehouse
import com.example.gdemobile.ui.IStateResponse

class WarehouseDao(stateResponse: IStateResponse?) : Dao(stateResponse) , IWarehouseDao {
    override suspend fun getWarehouses(): List<Warehouse>? =
        requestList<Warehouse>(GetWarehouses())

}