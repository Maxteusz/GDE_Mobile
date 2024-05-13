package com.example.gdemobile.apiConnect.enovaConnect.helpers.documenttypes

class InventoryType : IActionType {

    override var subType: ISubActionType? = InventorySubAction()

    class InventorySubAction : ISubActionType
    {
        override val symbol: String
            get() = "INW"

    }


}