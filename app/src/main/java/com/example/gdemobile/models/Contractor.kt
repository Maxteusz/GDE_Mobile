package com.example.gdemobile.models


class Contractor(Id: String?, Code: String?, Name: String?) : ObjectBase(Id, Code,Name),  java.io.Serializable {

    constructor() : this(null,null,null)
}