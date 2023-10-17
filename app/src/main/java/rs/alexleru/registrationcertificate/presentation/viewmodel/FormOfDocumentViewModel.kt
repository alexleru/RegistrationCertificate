package rs.alexleru.registrationcertificate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.domain.model.Document
import rs.alexleru.registrationcertificate.domain.usecase.AddDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.DeleteDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.GetDocumentUseCase
import rs.alexleru.registrationcertificate.presentation.state.Event
import rs.alexleru.registrationcertificate.presentation.state.FormAction
import rs.alexleru.registrationcertificate.presentation.state.FormEvent
import rs.alexleru.registrationcertificate.presentation.state.FormState1
import java.util.Calendar
import javax.inject.Inject

class FormOfDocumentViewModel
@Inject constructor(
    private val getDocumentUseCase: GetDocumentUseCase,
    private val addDocumentUseCase: AddDocumentUseCase,
    private val deleteDocumentUseCase: DeleteDocumentUseCase
) : ViewModel() {

    private var currentDocument: Document? = null
    private var originalFromDbDocument: Document? = null


    private val _formState = MutableStateFlow(FormState1().copy(isRefreshing = true))
    val formState = _formState.asStateFlow()

    private val _formEvent = MutableSharedFlow<Event<FormEvent>>()
    val formEvent = _formEvent.asSharedFlow()

    fun newDocument(){
        _formState.value = FormState1().copy(isRefreshing = false)
    }

    fun setDocument(documentId: Long) {
        viewModelScope.launch {
            val item = getDocumentUseCase(documentId)
            this@FormOfDocumentViewModel.currentDocument = item
            this@FormOfDocumentViewModel.originalFromDbDocument = item
            _formState.value = FormState1().copy(isRefreshing = false, content = currentDocument)
        }
    }

    fun handleAction(formAction: FormAction) {
        when (formAction) {
            is FormAction.Save -> saveForm(formAction.document)
            is FormAction.CloseWithCheck -> closeForm(formAction.document)
            is FormAction.ForceClose -> forceCloseForm()
            is FormAction.Delete -> deleteItem()
            is FormAction.ResetErrorField -> {
                when (formAction.name) {
                    FormAction.RESET_ERROR_NAME -> resetErrorName(formAction.document)
                    FormAction.RESET_ERROR_SURNAME -> resetErrorSurname(formAction.document)
                    FormAction.RESET_ERROR_ADDRESS -> resetErrorAddressStay(formAction.document)
                    FormAction.RESET_ERROR_REG_DATE -> resetErrorRegistration(formAction.document)
                }
            }
            is FormAction.ShowDatePickerDialog ->
                showDatePickerDialog(formAction.calendar, formAction.viewId)
            is FormAction.ShowChooseSexDialog -> showChooseSexDialog()
        }
    }

    private fun checkNotNullField(document: Document): Boolean {
        var result = true
        val isNameError = validateEmptyString(document.name)
        val isSurnameError = validateEmptyString(document.surname)
        val isAddressError = validateEmptyString(document.addressStay)
        val isDateRegistrationError = validateEmptyString(document.dateOfRegistration)
        if (isNameError || isSurnameError || isAddressError || isDateRegistrationError) {
            val copy = formState.value.errorContent.copy(
                name = isNameError,
                surname = isSurnameError,
                addressStay = isAddressError,
                dateOfRegistration = isDateRegistrationError
            )
            _formState.value = formState.value.copy(errorContent = copy)
            result = false
        }
        return result
    }

    private fun resetErrorName(document: Document) {
        val copy = formState.value.errorContent.copy(name = false)
        _formState.value = formState.value.copy(errorContent = copy, content = document)
    }

    private fun resetErrorSurname(document: Document) {
        val copy = formState.value.errorContent.copy(surname = false)
        _formState.value = formState.value.copy(errorContent = copy, content = document)
    }

    private fun resetErrorAddressStay(document: Document) {
        val copy = formState.value.errorContent.copy(addressStay = false)
        _formState.value = formState.value.copy(errorContent = copy, content = document)
    }

    private fun resetErrorRegistration(document: Document) {
        val copy = formState.value.errorContent.copy(dateOfRegistration = false)
        _formState.value = formState.value.copy(errorContent = copy, content = document)
    }

    private fun validateEmptyString(value: String): Boolean {
        return value.trim().isBlank()
    }

    private fun areContentsNotTheSame(document: Document): Boolean {
        getIdDocument(document)
        return this.originalFromDbDocument != this.currentDocument
    }

    private fun getIdDocument(document: Document) {
        var doc = document
        this.currentDocument?.let {
            doc = doc.copy(id = it.id)
        }
        this.currentDocument = doc
        _formState.value = formState.value.copy(content = doc) //TODO violates single responsibility
    }

    private fun saveForm(document: Document) {
        val areContentsNotTheSame = areContentsNotTheSame(document)
        val checkNotNullField = checkNotNullField(document)
        if (checkNotNullField && areContentsNotTheSame) {
            viewModelScope.launch {
                this@FormOfDocumentViewModel.currentDocument?.let { addDocumentUseCase(it) }
                forceCloseForm()
            }
        }
    }

    private fun closeForm(document: Document) {
        if (areContentsNotTheSame(document)) {
            viewModelScope.launch {
                _formEvent.emit(Event(FormEvent.WarningDialog))
            }
        } else {
            forceCloseForm()
        }
    }

    private fun forceCloseForm() {
        viewModelScope.launch {
            _formEvent.emit(Event(FormEvent.Close))
        }
    }

    private fun deleteItem() {
        viewModelScope.launch {
            this@FormOfDocumentViewModel.currentDocument?.let { deleteDocumentUseCase(documentId = it.id) }
            forceCloseForm() //TODO хорошо ли так делать? Стоит получить инфу, что элемент удалился?
        }
    }

    private fun showDatePickerDialog(calendar: Calendar?, viewId: Int) {
        viewModelScope.launch {
            _formEvent.emit(Event(FormEvent.DatePickerDialog(calendar, viewId)))
        }
    }

    private fun showChooseSexDialog() {
        viewModelScope.launch {
            _formEvent.emit(Event(FormEvent.ChooseSexDialog))
        }
    }
}