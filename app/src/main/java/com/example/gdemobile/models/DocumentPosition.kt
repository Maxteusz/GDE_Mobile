package com.example.gdemobile.models

class DocumentPosition (id : String, code :  String,  name : String,unit: String,   barcode: String, var amount : Double)
    : Cargo(id,code,name,barcode,unit) {


    class DocumentDefinition() {
        var id: String = ""
        var name: String = ""
        var symbol: String = ""

        val fullName: String
            get() {
                if (name.isNullOrEmpty())
                    return "$symbol"
                else
                    return "$symbol - $name"
            }

    }
}