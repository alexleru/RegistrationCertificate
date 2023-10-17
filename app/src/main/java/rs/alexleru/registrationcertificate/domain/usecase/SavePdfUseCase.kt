package rs.alexleru.registrationcertificate.domain.usecase

interface SavePdfUseCase {
    operator fun invoke(documentId: Long)
}