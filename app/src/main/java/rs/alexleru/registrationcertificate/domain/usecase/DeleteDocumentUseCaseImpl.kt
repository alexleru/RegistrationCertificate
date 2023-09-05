package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.DocumentRepository
import java.util.UUID
import javax.inject.Inject

class DeleteDocumentUseCaseImpl @Inject constructor(private val repository: DocumentRepository) :
    DeleteDocumentUseCase {
    override fun invoke(documentId: Long) {
        repository.deleteDocument(documentId)
    }
}