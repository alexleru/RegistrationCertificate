package rs.alexleru.registrationcertificate.di.module

import dagger.Module
import dagger.Provides
import rs.alexleru.registrationcertificate.data.MockDataForDB
import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao

@Module
class TestModule {

    @Provides
    fun provideTest(documentDao: DocumentDao): MockDataForDB {
        return MockDataForDB(
            documentDao
        )
    }
}