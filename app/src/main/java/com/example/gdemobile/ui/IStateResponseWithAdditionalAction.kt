package com.example.gdemobile.ui

interface IStateResponseWithAdditionalAction : IStateResponse {
   fun OnSucces(additionalAction: (() -> Unit)?)


}