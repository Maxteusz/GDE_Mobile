package com.example.gdemobile.apiConnect.enovaConnect.daos.documentDefinition

import com.example.gdemobile.models.DocumentDefinition

interface IDocumentDefinitionDao {
    suspend fun getdDcumentDefinitions(documentType : String) : List<DocumentDefinition>?
}