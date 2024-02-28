package com.example.gdemobile.helpers

object DocumentType {
   lateinit var mainType : IMainType

    class Receiving : IMainType{
        val inside = "PWPW"
        val outside = "PWPW"
    }

    class Issuing : IMainType{
        val inside = "PWPW"
        val outside = "PWPW"
    }


    interface IMainType
}