package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentDefinition() {
     @SerializedName("Id")
     var  id : String = ""
     var name : String = ""
     @SerializedName("Symbol")
     var symbol : String = ""

     val fullName : String get() {
          if (name.isNullOrEmpty())
          return "$symbol"
          else
               return "$symbol - $name"
     }





}