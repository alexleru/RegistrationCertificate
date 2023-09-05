package rs.alexleru.registrationcertificate.data.mapper

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import rs.alexleru.registrationcertificate.domain.model.Document

//class DocumentMapper {

    fun DocumentModelDB.toDomain() = Document(
        id = this.id,
        creationDateTime = this.creationDateTime,
        lastChangeDateTime = this.lastChangeDateTime,
        surname = this.surname,
        name = this.name,
        dateOfBirth = this.dateOfBirth,
        sex = this.sex,
        placeOfBirthday = this.placeOfBirthday,
        typeOfDocument = this.typeOfDocument,
        numberOfDocument = this.numberOfDocument,
        typeOfVisa = this.typeOfVisa,
        numberOfVisa = this.numberOfVisa,
        placeOfVisa = this.placeOfVisa,
        dateIn = this.dateIn,
        entryInto = this.entryInto,
        addressStay = this.addressStay,
        nameOfHost = this.nameOfHost,
        numberIdOfHost = this.numberIdOfHost,
        dateOfRegistration = this.dateOfRegistration,
        note = this.note
    )

    fun Document.toDB() = DocumentModelDB(
        id = this.id,
        creationDateTime = this.creationDateTime,
        lastChangeDateTime = this.lastChangeDateTime,
        surname = this.surname,
        name = this.name,
        dateOfBirth = this.dateOfBirth,
        sex = this.sex,
        placeOfBirthday = this.placeOfBirthday,
        typeOfDocument = this.typeOfDocument,
        numberOfDocument = this.numberOfDocument,
        typeOfVisa = this.typeOfVisa,
        numberOfVisa = this.numberOfVisa,
        placeOfVisa = this.placeOfVisa,
        dateIn = this.dateIn,
        entryInto = this.entryInto,
        addressStay = this.addressStay,
        nameOfHost = this.nameOfHost,
        numberIdOfHost = this.numberIdOfHost,
        dateOfRegistration = this.dateOfRegistration,
        note = this.note
    )
//}