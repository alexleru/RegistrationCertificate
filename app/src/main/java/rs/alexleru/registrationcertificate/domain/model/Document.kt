package rs.alexleru.registrationcertificate.domain.model

import java.util.UUID

data class Document(
    val id: Long,
    val creationDateTime: Long,
    val lastChangeDateTime: Long,
    val surname: String,
    val name: String,
    val dateOfBirth: Long?,
    val sex: Char?,
    val placeOfBirthday: String?,
    val typeOfDocument: String?,
    val numberOfDocument: String?,
    val typeOfVisa: String?,
    val numberOfVisa: String?,
    val placeOfVisa: String?,
    val dateIn: Long?,
    val entryInto: String?,
    val addressStay: String,
    val nameOfHost: String?,
    val numberIdOfHost: String?,
    val dateOfRegistration: Long,
    val note: String?
)
