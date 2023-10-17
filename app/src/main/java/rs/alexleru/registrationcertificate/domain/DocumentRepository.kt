package rs.alexleru.registrationcertificate.domain

import kotlinx.coroutines.flow.Flow
import rs.alexleru.registrationcertificate.domain.model.Document

interface DocumentRepository {

    fun getListOfDocuments(): Flow<List<Document>>

    suspend fun getDocument(documentId: Long): Document

    suspend fun addDocument(document: Document)

    suspend fun deleteDocument(documentId: Long)


}