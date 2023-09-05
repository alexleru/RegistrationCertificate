package rs.alexleru.registrationcertificate.domain.usecase

import kotlinx.coroutines.flow.Flow
import rs.alexleru.registrationcertificate.domain.DocumentRepository
import rs.alexleru.registrationcertificate.domain.model.Document
import javax.inject.Inject

class GetListOfDocumentUseCaseImpl @Inject constructor(private val repository: DocumentRepository) :
    GetListOfDocumentUseCase {
    override fun invoke(): Flow<List<Document>> {
        return repository.getListOfDocuments()
    }
}