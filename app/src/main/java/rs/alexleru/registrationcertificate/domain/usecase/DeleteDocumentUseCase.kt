package rs.alexleru.registrationcertificate.domain.usecase

interface DeleteDocumentUseCase {
    operator fun invoke(documentId: Long)
}