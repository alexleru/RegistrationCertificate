package rs.alexleru.registrationcertificate.data.util

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import rs.alexleru.registrationcertificate.domain.model.Document

fun DocumentModelDB.toDomain() = Document(
    id = this.id,
    surname = this.surname,
    name = this.name,
    dateOfBirth = this.dateOfBirth?.toCalendar(),
    sex = this.sex,
    placeOfBirthday = this.placeOfBirthday,
    nationality = this.nationality,
    typeOfDocument = this.typeOfDocument,
    numberOfDocument = this.numberOfDocument,
    typeOfVisa = this.typeOfVisa,
    numberOfVisa = this.numberOfVisa,
    placeOfVisa = this.placeOfVisa,
    dateIn = this.dateIn?.toCalendar(),
    entryInto = this.entryInto,
    addressStay = this.addressStay,
    nameOfHost = this.nameOfHost,
    numberIdOfHost = this.numberIdOfHost,
    dateOfRegistration = this.dateOfRegistration.toCalendar(),
    note = this.note
)

fun Document.toDB() = DocumentModelDB(
    id = this.id,
    surname = this.surname,
    name = this.name,
    dateOfBirth = this.dateOfBirth?.toLong(),
    sex = this.sex,
    placeOfBirthday = this.placeOfBirthday,
    nationality = this.nationality,
    typeOfDocument = this.typeOfDocument,
    numberOfDocument = this.numberOfDocument,
    typeOfVisa = this.typeOfVisa,
    numberOfVisa = this.numberOfVisa,
    placeOfVisa = this.placeOfVisa,
    dateIn = this.dateIn?.toLong(),
    entryInto = this.entryInto,
    addressStay = this.addressStay,
    nameOfHost = this.nameOfHost,
    numberIdOfHost = this.numberIdOfHost,
    dateOfRegistration = this.dateOfRegistration.toLong(),
    note = this.note
)