package rs.alexleru.registrationcertificate.data.databaseRoom.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rs.alexleru.registrationcertificate.data.databaseRoom.model.DocumentModelDB
import rs.alexleru.registrationcertificate.domain.model.Document
import java.util.UUID

@Dao
interface DocumentDao {

    @Query("SELECT * FROM document")
    suspend fun getListOfDocument(): List<DocumentModelDB>

    @Query("SELECT * FROM document WHERE id =:documentId")
    fun getDocument(documentId: Long): DocumentModelDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDocument(document: DocumentModelDB)

    @Query("DELETE FROM document where id=:documentId")
    fun deleteDocument(documentId: Long)
}