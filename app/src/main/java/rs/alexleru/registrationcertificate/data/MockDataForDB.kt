package rs.alexleru.registrationcertificate.data

import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao
import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import javax.inject.Inject

class MockDataForDB @Inject constructor(documentDao: DocumentDao) {

    init {
        val documentModelDB = DocumentModelDB(
            id = 0,
            creationDateTime = System.currentTimeMillis(),
            lastChangeDateTime = System.currentTimeMillis(),
            name = "Hi",
            surname = "World",
            addressStay = "Moscow",
            dateOfRegistration = 111
        )
        val documentModelDB2 = DocumentModelDB(
            id = 0,
            creationDateTime = System.currentTimeMillis(),
            lastChangeDateTime = System.currentTimeMillis(),
            name = "Bye",
            surname = "World",
            addressStay = "Kazan",
            dateOfRegistration = 222
        )
        documentDao.addDocument(documentModelDB)
        documentDao.addDocument(documentModelDB2)
    }
}