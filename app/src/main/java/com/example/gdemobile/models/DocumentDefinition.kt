package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentDefinition() {
     @SerializedName("Id")
     var  id : String = ""
     var name : String = ""
     @SerializedName("Symbol")
     var symbol : String = ""
     @SerializedName("Ilosc")
     val amount : Double = 0.0

     val fullName : String get() {
          if (name.isNullOrEmpty())
          return symbol
          else
               return "$symbol - $name"
     }





}