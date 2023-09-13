package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.DocumentRepository
import rs.alexleru.registrationcertificate.domain.model.Document
import javax.inject.Inject

class GetDocumentUseCaseImpl @Inject constructor(private val repository: DocumentRepository) :
    GetDocumentUseCase {
    override suspend operator fun invoke(documentId: Long): Document {
        return repository.getDocument(documentId)
    }
}