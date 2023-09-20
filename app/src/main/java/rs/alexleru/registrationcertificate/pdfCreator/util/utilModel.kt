package rs.alexleru.registrationcertificate.pdfCreator.util

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import rs.alexleru.registrationcertificate.pdfCreator.model.DocumentModelPdf
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun DocumentModelDB.toPDF() = DocumentModelPdf(
    surname = this.surname,
    name = this.name,
    dateOfBirth = this.dateOfBirth?.toStr() ?: "",
    sex = this.sex.toString(),
    placeOfBirth = this.placeOfBirthday ?: "",
    nationality = this.nationality  ?: "",
    typeOfDocument = this.typeOfDocument  ?: "",
    numberOfDocument = this.numberOfDocument  ?: "",
    dateIn = this.dateIn?.toStr()  ?: "",
    entryInto = this.entryInto ?: "",
    addressStay = this.addressStay,
    nameOfHost = this.nameOfHost ?: "",
    numberIdOfHost = this.numberIdOfHost ?: "",
    dateOfRegistration = this.dateOfRegistration.toStr()
)

internal fun Long.toStr(): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = this@toStr }
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
        return format.format(calendar.time)
}

