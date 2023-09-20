package rs.alexleru.registrationcertificate.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao
import rs.alexleru.registrationcertificate.data.util.toDB
import rs.alexleru.registrationcertificate.data.util.toDomain
import rs.alexleru.registrationcertificate.domain.DocumentRepository
import rs.alexleru.registrationcertificate.domain.model.Document
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao,
) : DocumentRepository {


    override fun getListOfDocuments(): Flow<List<Document>> {
        return flow {
            delay(1000) //TODO удалить!!!
            documentDao.getListOfDocument().collect {
                this@flow.emit(it.map { doc -> doc.toDomain() }.toList())
            }
        }

    }

    override suspend fun getDocument(documentId: Long): Document {

        delay(1000)//TODO удалить!!!

        return documentDao.getDocument(documentId = documentId).toDomain()
    }

    override suspend fun addDocument(document: Document) {
        val documentModelDB = document.toDB()
        documentDao.addDocument(document = documentModelDB)
    }

    override suspend fun deleteDocument(documentId: Long) {
        documentDao.deleteDocument(documentId = documentId)
    }
}