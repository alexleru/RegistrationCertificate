package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.model.Document

interface GetDocumentUseCase {
    suspend operator fun invoke(documentId: Long): Document
}