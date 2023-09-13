package rs.alexleru.registrationcertificate.presentation.state

import java.util.Calendar

sealed class FormEvent {
    object Close: FormEvent()
    object WarningDialog: FormEvent()
    data class DatePickerDialog(val calendar: Calendar?, val viewId: Int): FormEvent()
}