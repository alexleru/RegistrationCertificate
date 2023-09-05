package rs.alexleru.registrationcertificate.data.databaseRoom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "document")
data class DocumentModelDB(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val creationDateTime: Long,
    val lastChangeDateTime: Long,
    val surname: String,
    val name: String,
    val dateOfBirth: Long? = null,
    val sex: Char? = null,
    val placeOfBirthday: String? = null,
    val typeOfDocument: String? = null,
    val numberOfDocument: String? = null,
    val typeOfVisa: String? = null,
    val numberOfVisa: String? = null,
    val placeOfVisa: String? = null,
    val dateIn: Long? = null,
    val entryInto: String? = null,
    val addressStay: String,
    val nameOfHost: String? = null,
    val numberIdOfHost: String? = null,
    val dateOfRegistration: Long,
    val note: String? = null
)
