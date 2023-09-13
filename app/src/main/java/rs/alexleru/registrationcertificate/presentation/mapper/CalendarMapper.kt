package rs.alexleru.registrationcertificate.presentation.mapper

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.toCalendar(): Calendar? {
    if (this.isBlank()) {
        return null
    }
    val c = Calendar.getInstance()
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    c.time = format.parse(this) as Date
    return c
}

fun Calendar.toStr(): String {
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    return format.format(this.time)
}
