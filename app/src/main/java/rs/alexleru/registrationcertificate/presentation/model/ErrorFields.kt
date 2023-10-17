package rs.alexleru.registrationcertificate.presentation.model

data class ErrorFields(
    val name: Boolean = false,
    val surname: Boolean = false,
    val addressStay: Boolean = false,
    val dateOfRegistration: Boolean = false
)