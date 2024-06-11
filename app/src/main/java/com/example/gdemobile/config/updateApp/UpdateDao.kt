package com.example.gdemobile.config.updateApp

import com.example.gdemobile.apiConnect.enovaConnect.daos.Dao

import com.example.gdemobile.ui.IStateResponse

class UpdateDao(stateResult: IStateResponse) : Dao(stateResult) {

    suspend fun downloadFileInString(test: String): String? {
        return requestObject<String>(UpdateAppConnectionInformation())

    }
}