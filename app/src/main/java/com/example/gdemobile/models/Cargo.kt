package com.example.gdemobile.models

open class Cargo(val code:  String, val name: String, val barcode: String, val unit : String): ObjectBase(){
    constructor(id : String, code: String, name: String, barcode : String, unit : String) : this(code,name,barcode,unit)


}