package rs.alexleru.registrationcertificate.data.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

//internal fun Calendar.toLong() = this.timeInMillis
//internal fun Long.toCalendar() = Calendar.getInstance().apply { timeInMillis = this@toCalendar }
//
//
//internal fun String.toCalendar(): Calendar? {
//    if (this.isBlank()) {
//        return null
//    }
//    val c = Calendar.getInstance()
//    val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
//    c.time = format.parse(this) as Date
//    return c
//}
//
//internal fun Calendar.toStr(): String {
//    val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
//    return format.format(this.time)
//}

internal fun String.toLongNull(): Long?{
    if(this.isNotBlank()) {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
        calendar.time = format.parse(this) as Date
        return calendar.timeInMillis
    }
    return null
}

internal fun String.toLongNotNull(): Long{
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
        calendar.time = format.parse(this) as Date
        return calendar.timeInMillis
}
internal fun Long?.toStr(): String {
    if(this != null) {
        val calendar = Calendar.getInstance().apply { timeInMillis = this@toStr }
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
        return format.format(calendar.time)
    }
    return ""
}