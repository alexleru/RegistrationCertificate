package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document


sealed class FormState {
    object Loading: FormState()
    object Error: FormState()
    data class Content(val document: Document, val error: String? = null): FormState()
}