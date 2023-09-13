package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document

sealed class ListState {
    object Loading : ListState()
    object Error : ListState()
    data class Content(val listOfDocuments: List<Document>) : ListState()
}
