package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document


sealed class DocumentState {
    object Loading: DocumentState()
    object Error: DocumentState()
    data class Content(val document: Document): DocumentState()
}