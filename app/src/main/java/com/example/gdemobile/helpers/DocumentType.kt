package com.example.gdemobile.helpers

object DocumentType {
   lateinit var mainType : IMainType


    class Receiving : IMainType{
        override val inside = "PWPW"
        override val outside = "PWPW"
    }

    class Issuing : IMainType{
        override val inside = "PWPW"
        override val outside = "PWPW"
    }


    interface IMainType
    {
        val inside : String
        val outside :  String
    }
}