package rs.alexleru.registrationcertificate.domain.usecase

import kotlinx.coroutines.flow.Flow
import rs.alexleru.registrationcertificate.domain.model.Document

interface GetListOfDocumentUseCase {
    operator fun invoke(): Flow<List<Document>>
}