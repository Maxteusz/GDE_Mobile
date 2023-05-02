package com.example.gdemobile.models

class DocumentDefinition() {
     var  id : String = ""
     var name : String = ""
     var symbol : String = ""

     val fullName : String get() {
          if (name.isNullOrEmpty())
          return "$symbol"
          else
               return "$symbol - $name"
     }





}