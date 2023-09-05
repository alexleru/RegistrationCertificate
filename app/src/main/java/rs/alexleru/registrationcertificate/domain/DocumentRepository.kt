package rs.alexleru.registrationcertificate.domain

import kotlinx.coroutines.flow.Flow
import rs.alexleru.registrationcertificate.domain.model.Document
import java.util.UUID

interface DocumentRepository {

    fun getListOfDocuments(): Flow<List<Document>>

    fun getDocument(documentId: Long): Document

    fun addDocument(document: Document)

    fun deleteDocument(documentId: Long)
}