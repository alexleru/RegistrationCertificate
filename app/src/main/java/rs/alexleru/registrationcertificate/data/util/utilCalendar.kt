package rs.alexleru.registrationcertificate.data.util

import java.util.Calendar

internal fun Calendar.toLong() = this.timeInMillis
internal fun Long.toCalendar() = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

