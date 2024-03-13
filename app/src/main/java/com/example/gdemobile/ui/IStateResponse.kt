package com.example.gdemobile.ui

interface IStateResponse {
   fun OnLoading()
    suspend fun OnError(message : String)
    fun  OnSucces();


}