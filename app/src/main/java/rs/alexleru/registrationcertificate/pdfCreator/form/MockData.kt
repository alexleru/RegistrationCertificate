package rs.alexleru.registrationcertificate.pdfCreator.form

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB

class MockData {

    fun mock(): DocumentModelDB {
        return DocumentModelDB(
            id = 0,
            surname = "Petrov",
            name = "Ivan",
            dateOfBirth = 0,
            sex = 'M',
            placeOfBirthday = "Piter, Russia",
            nationality = "Russian",
            typeOfDocument = "Passport",
            numberOfDocument = "99 999999",
            typeOfVisa = null,
            numberOfVisa = null,
            placeOfVisa = null,
            dateIn = 0,
            entryInto = "Airport",
            addressStay = "Kutuzovka",
            nameOfHost = "Danil Bagrov",
            numberIdOfHost = "11111111111111",
            dateOfRegistration = 0,
            note = null
        )
    }
}