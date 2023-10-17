package rs.alexleru.registrationcertificate.pdfCreator.form

import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB

class MockData {

    fun mock(): DocumentModelDB {
        return DocumentModelDB(
            id = 0,
            surname = "Petrov",
            name = "Ivan",
            dateOfBirth = 12,
            sex = "l",
            placeOfBirthday = "Piter, Russia",
            nationality = "Russian",
            typeOfDocument = "Passport",
            numberOfDocument = "99 999999",
            dateIn = 122,
            entryInto = "Airport",
            addressStay = "Profesora Mihala Filipa 50, Stara Pazova 22300",
            nameOfHost = "Danil Bagrov",
            numberIdOfHost = "11111111111111",
            dateOfRegistration = 122,

        )
    }
}