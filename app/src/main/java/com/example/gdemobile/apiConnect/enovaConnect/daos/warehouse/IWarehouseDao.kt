package com.example.gdemobile.apiConnect.enovaConnect.daos.warehouse

import com.example.gdemobile.models.Warehouse

interface IWarehouseDao {
    suspend fun getWarehouses() : List<Warehouse>?
}