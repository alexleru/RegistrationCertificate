package rs.alexleru.registrationcertificate.presentation.state

import rs.alexleru.registrationcertificate.domain.model.Document
import java.util.Calendar

sealed class FormAction {
    data class Save(val document: Document): FormAction()
    data class CloseWithCheck(val document: Document) : FormAction()
    data class ShowDatePickerDialog(val calendar: Calendar?, val viewId: Int): FormAction()
    data class ResetErrorField(val name: String, val document: Document): FormAction()
    object ForceClose: FormAction()
    object Delete: FormAction()
    object ShowChooseSexDialog: FormAction()

    companion object{
        const val RESET_ERROR_NAME = "Error name"
        const val RESET_ERROR_SURNAME = "Error surname"
        const val RESET_ERROR_ADDRESS = "Error address"
        const val RESET_ERROR_REG_DATE = "Error reg.date"
    }
}