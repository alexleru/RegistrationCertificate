package rs.alexleru.registrationcertificate.domain.model

import java.util.Calendar

data class Document(
    val id: Long = 0,
    val surname: String = "",
    val name: String = "",
    val dateOfBirth: Calendar? = null,
    val sex: Char? = null,
    val placeOfBirthday: String? = null,
    val nationality: String? = null,
    val typeOfDocument: String? = null,
    val numberOfDocument: String? = null,
    val typeOfVisa: String? = null,
    val numberOfVisa: String? = null,
    val placeOfVisa: String? = null,
    val dateIn: Calendar? = null,
    val entryInto: String? = null,
    val addressStay: String,
    val nameOfHost: String?,
    val numberIdOfHost: String? = null,
    val dateOfRegistration: Calendar = Calendar.getInstance(),
    val note: String? = null
)
