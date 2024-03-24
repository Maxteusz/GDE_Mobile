package com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes

class Issuance : IActionType {
    override var subType: ISubActionType? = null

    class Internal  : ISubActionType {
        override val symbol: String
            get() = "WWE"
    }

    class External : ISubActionType {
        override val symbol: String
            get() = "WZE"
    }

}