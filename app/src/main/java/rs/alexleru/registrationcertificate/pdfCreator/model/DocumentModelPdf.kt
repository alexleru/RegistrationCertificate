package rs.alexleru.registrationcertificate.pdfCreator.model

data class DocumentModelPdf(
    val surname: String,
    val name: String,
    val dateOfBirth: String = "",
    val sex: String = "",
    val placeOfBirth: String = "",
    val nationality: String = "",
    val typeOfDocument: String = "",
    val numberOfDocument: String = "",
    val dateIn: String = "",
    val entryInto: String = "",
    val addressStay: String,
    val nameOfHost: String = "",
    val numberIdOfHost: String = "",
    val dateOfRegistration: String,
)
