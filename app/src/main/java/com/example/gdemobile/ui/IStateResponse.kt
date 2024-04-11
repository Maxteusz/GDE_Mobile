package com.example.gdemobile.ui

interface IStateResponse {
   fun onLoading()
    suspend fun onError(message : String)
    fun  onSuccess();


}