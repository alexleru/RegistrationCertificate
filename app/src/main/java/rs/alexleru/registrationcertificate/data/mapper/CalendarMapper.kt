package rs.alexleru.registrationcertificate.data.mapper

import java.util.Calendar

internal fun Calendar.toLong() = this.timeInMillis

internal fun Long.toCalendar() = Calendar.getInstance().apply { timeInMillis = this@toCalendar }
