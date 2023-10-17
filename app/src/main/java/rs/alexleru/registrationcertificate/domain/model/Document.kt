package rs.alexleru.registrationcertificate.domain.model

data class Document(
    val id: Long = UNDEFINED_ID,
    val surname: String = "",
    val name: String = "",
    val dateOfBirth: String? = null,
    val sex: String? = null,
    val placeOfBirthday: String? = null,
    val nationality: String? = null,
    val typeOfDocument: String? = null,
    val numberOfDocument: String? = null,
    val dateIn: String? = null,
    val entryInto: String? = null,
    val addressStay: String = "",
    val nameOfHost: String? = null,
    val numberIdOfHost: String? = null,
    val dateOfRegistration: String = ""
) {
    companion object{
        private const val UNDEFINED_ID = 0L
    }
}
