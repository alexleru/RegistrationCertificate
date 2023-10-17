package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.presentation.model.ErrorFields


sealed class FormState {
    object Loading : FormState()
    object Error : FormState()
    data class Content(
        val document: Document,
        val errorName: Boolean? = null,
        val errorSurname: Boolean? = null,
        val errorAddress: Boolean? = null,
        val errorRegistrationDate: Boolean? = null,
    ) : FormState()

    object EmptyContent : FormState()
}

data class FormState1(
    val isRefreshing: Boolean = false,
    val content: Document? = null,
    val errorContent: ErrorFields = ErrorFields(),
    val error: Throwable? = null,
)