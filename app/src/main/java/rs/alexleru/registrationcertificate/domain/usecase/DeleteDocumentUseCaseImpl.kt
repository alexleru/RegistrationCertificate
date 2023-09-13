package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.DocumentRepository
import javax.inject.Inject

class DeleteDocumentUseCaseImpl @Inject constructor(private val repository: DocumentRepository) :
    DeleteDocumentUseCase {
    override suspend operator fun invoke(documentId: Long) {
        repository.deleteDocument(documentId)
    }
}