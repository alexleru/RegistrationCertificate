package rs.alexleru.registrationcertificate.data.databaseRoom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB

@Database(entities = [DocumentModelDB::class], version = 4, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun documentDao(): DocumentDao

    companion object {
        private var db: AppDataBase? = null
        private const val DBNAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDataBase {
            db?.let { return it }
            synchronized(LOCK) {
                db?.let { return it }

                val instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    DBNAME
                ).fallbackToDestructiveMigration()
                    .build()

                db = instance
                return instance
            }
        }

    }

}