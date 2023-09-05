package rs.alexleru.registrationcertificate.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import rs.alexleru.registrationcertificate.data.databaseRoom.db.AppDataBase
import rs.alexleru.registrationcertificate.data.databaseRoom.db.DocumentDao
import rs.alexleru.registrationcertificate.di.key.AppScope

@Module(includes = [TestModule::class])
class DBModule {

    @AppScope
    @Provides
    fun providesGetDB(context: Context): DocumentDao {
        return AppDataBase.getInstance(context).documentDao()
    }
}