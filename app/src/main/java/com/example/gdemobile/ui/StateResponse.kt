package com.example.gdemobile.ui

interface StateResponse {
    fun OnLoading()
    suspend fun OnError(message : String)
    fun  OnSucces();


}