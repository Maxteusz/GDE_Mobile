package com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes

class Acceptance : IActionType {
    override var subType: ISubActionType? = null




    class Internal  : ISubActionType {
        override val symbol: String
            get() = "PWE"
    }

    class External : ISubActionType {
        override val symbol: String
            get() = "PZE"
    }

}