package com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes

class IssuanceDocument : IDocumentType {
    override var subType: ISubDocumentType? = null

    class Internal  : ISubDocumentType {
        override val symbol: String
            get() = "WWE"
    }

    class External : ISubDocumentType {
        override val symbol: String
            get() = "WZE"
    }

}