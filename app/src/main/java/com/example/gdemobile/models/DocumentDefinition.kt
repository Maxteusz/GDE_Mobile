package com.example.gdemobile.models

import com.google.gson.annotations.SerializedName

class DocumentDefinition {

     @SerializedName("ID")
     var  id : Int= 0
     @SerializedName("Symbol")
     var symbol : String = ""
     @SerializedName("Nazwa")
     val name : String = ""

}