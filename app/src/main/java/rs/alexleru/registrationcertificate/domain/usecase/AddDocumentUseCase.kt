package rs.alexleru.registrationcertificate.domain.usecase

import rs.alexleru.registrationcertificate.domain.model.Document

interface AddDocumentUseCase {
    suspend operator fun invoke(document: Document)
}