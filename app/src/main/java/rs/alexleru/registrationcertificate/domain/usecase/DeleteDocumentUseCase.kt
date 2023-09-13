package rs.alexleru.registrationcertificate.domain.usecase

interface DeleteDocumentUseCase {
    suspend operator fun invoke(documentId: Long)
}