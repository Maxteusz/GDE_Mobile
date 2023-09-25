package com.example.gdemobile.ui

interface StateResponse {
    fun OnLoading()
    fun OnError(message : String)
    fun <T> OnSucces(result : T? = null);


}