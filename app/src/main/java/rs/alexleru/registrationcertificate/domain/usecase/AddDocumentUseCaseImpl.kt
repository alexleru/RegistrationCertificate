package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.DocumentRepository
import rs.alexleru.registrationcertificate.domain.model.Document
import javax.inject.Inject

class AddDocumentUseCaseImpl @Inject constructor(private val repository: DocumentRepository) : AddDocumentUseCase {
    override fun invoke(document: Document) {
        repository.addDocument(document)
    }
}