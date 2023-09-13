package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document
import java.util.Calendar

sealed class FormAction {
    data class Save(val document: Document): FormAction()
    data class CloseWithCheck(val document: Document) : FormAction()
    data class ShowDatePickerDialog(val calendar: Calendar?, val viewId: Int): FormAction()
    object ForceClose: FormAction()
    object Delete: FormAction()
}