package rs.alexleru.registrationcertificate.data.databaseRoom.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB

@Dao
interface DocumentDao {

    @Query("SELECT * FROM document")
    fun getListOfDocument(): Flow<List<DocumentModelDB>>

    @Query("SELECT * FROM document WHERE id =:documentId")
    suspend fun getDocument(documentId: Long): DocumentModelDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDocument(document: DocumentModelDB)

    @Query("DELETE FROM document where id=:documentId")
    suspend fun deleteDocument(documentId: Long)
}