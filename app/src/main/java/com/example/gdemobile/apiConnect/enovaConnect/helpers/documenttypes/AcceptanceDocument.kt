package com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes

class AcceptanceDocument : IDocumentType {
    override var subType: ISubDocumentType? = null




    class Internal  : ISubDocumentType {
        override val symbol: String
            get() = "PWE"
    }

    class External : ISubDocumentType {
        override val symbol: String
            get() = "PZE"
    }

}