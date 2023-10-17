package rs.alexleru.registrationcertificate.data.util

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import rs.alexleru.registrationcertificate.domain.model.Document

fun DocumentModelDB.toDomain() = Document(
    id = this.id,
    surname = this.surname,
    name = this.name,
    dateOfBirth = this.dateOfBirth.toStr(),
    sex = this.sex,
    placeOfBirthday = this.placeOfBirthday,
    nationality = this.nationality,
    typeOfDocument = this.typeOfDocument,
    numberOfDocument = this.numberOfDocument,
    dateIn = this.dateIn.toStr(),
    entryInto = this.entryInto,
    addressStay = this.addressStay,
    nameOfHost = this.nameOfHost,
    numberIdOfHost = this.numberIdOfHost,
    dateOfRegistration = this.dateOfRegistration.toStr(),
)

fun Document.toDB() = DocumentModelDB(
    id = this.id,
    surname = this.surname,
    name = this.name,
    dateOfBirth = this.dateOfBirth?.toLongNull(),
    sex = this.sex,
    placeOfBirthday = this.placeOfBirthday,
    nationality = this.nationality,
    typeOfDocument = this.typeOfDocument,
    numberOfDocument = this.numberOfDocument,
    dateIn = this.dateIn?.toLongNull(),
    entryInto = this.entryInto,
    addressStay = this.addressStay,
    nameOfHost = this.nameOfHost,
    numberIdOfHost = this.numberIdOfHost,
    dateOfRegistration = this.dateOfRegistration.toLongNotNull(),
)