package com.example.gdemobile.apiConnect.enovaConnect.methods.document

import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IConnectEnovaMethod
import com.example.gdemobile.apiConnect.enovaConnect.methods.interfaces.IDto
import com.example.gdemobile.models.Document

class CreateNewDocument(val document : Document?
) : IConnectEnovaMethod {
    override val methodName: String
        get() = "UtworzNowyDokumentPrzyjeciaZewnetrznegoMagazynowego"
    override val methodService: String
        get() = TODO("Not yet implemented")
    override val dto: IDto
        get() = TODO("Not yet implemented")


}


