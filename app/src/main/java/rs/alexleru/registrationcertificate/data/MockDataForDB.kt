package rs.alexleru.registrationcertificate.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao
import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import javax.inject.Inject

class MockDataForDB @Inject constructor(documentDao: DocumentDao) {

    init {
        val documentModelDB = DocumentModelDB(
            id = 0,
            name = "Hi",
            surname = "World",
            addressStay = "Moscow",
            dateOfRegistration = 111
        )
        val documentModelDB2 = DocumentModelDB(
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

        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            documentDao.addDocument(documentModelDB)
            documentDao.addDocument(documentModelDB2)
        }
    }
}