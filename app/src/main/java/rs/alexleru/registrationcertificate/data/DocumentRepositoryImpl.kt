package rs.alexleru.registrationcertificate.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao
import rs.alexleru.registrationcertificate.data.mapper.toDB
import rs.alexleru.registrationcertificate.data.mapper.toDomain
import rs.alexleru.registrationcertificate.domain.DocumentRepository
import rs.alexleru.registrationcertificate.domain.model.Document
import java.util.UUID
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val documentDao: DocumentDao,
) : DocumentRepository {


    override fun getListOfDocuments(): Flow<List<Document>> {
        return flow {
            delay(3000) //TODO удалить!!!
            val listOfDocument =
                documentDao.getListOfDocument().map { it.toDomain() }.toList()
            emit(listOfDocument)
        }

    }

    override fun getDocument(documentId: Long): Document {
        return documentDao.getDocument(documentId = documentId).toDomain()
    }

    override fun addDocument(document: Document) {
        val documentModelDB = document.toDB()
        documentDao.addDocument(document = documentModelDB)
    }

    override fun deleteDocument(documentId: Long) {
        documentDao.deleteDocument(documentId = documentId)
    }
}