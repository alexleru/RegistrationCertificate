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
import rs.alexleru.registrationcertificate.presentation.state.FormState
import java.util.Calendar
import javax.inject.Inject

class FormOfDocumentViewModel
@Inject constructor(
    private val getDocumentUseCase: GetDocumentUseCase,
    private val addDocumentUseCase: AddDocumentUseCase,
    private val deleteDocumentUseCase: DeleteDocumentUseCase
) : ViewModel() {

    private var document: Document? = null

    private val _formState = MutableStateFlow<FormState>(FormState.Loading)
    val formState = _formState.asStateFlow()

    private val _formEvent = MutableSharedFlow<Event<FormEvent>>()
    val formEvent = _formEvent.asSharedFlow()

    fun setDocument(documentId: Long) {
        viewModelScope.launch {
            val item = getDocumentUseCase(documentId)
            this@FormOfDocumentViewModel.document = item
            _formState.value = FormState.Content(item)
        }
    }

    fun handleAction(formAction: FormAction) {
        when (formAction) {
            is FormAction.Save -> saveForm(formAction.document)
            is FormAction.CloseWithCheck -> closeForm(formAction.document)
            is FormAction.ForceClose -> forceCloseForm()
            is FormAction.Delete -> deleteItem()
            is FormAction.ShowDatePickerDialog ->
                showDatePickerDialog(formAction.calendar, formAction.viewId)
        }
    }

    private fun areContentsNotTheSame(document: Document): Boolean {
        var doc = document
        this.document?.let {
            doc = doc.copy(id = it.id)
        }
        return this.document != doc
    }

    private fun saveForm(document: Document) {
        if (areContentsNotTheSame(document)) {
            viewModelScope.launch {
                addDocumentUseCase(document)
            }
            viewModelScope.launch {
                _formEvent.emit(Event(FormEvent.Close))
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
        this.document?.let {
            viewModelScope.launch {
                deleteDocumentUseCase(documentId = it.id)
                _formEvent.emit(Event(FormEvent.Close)) //TODO хорошо ли так делать? Стоит получить инфу, что элемент удалился?
            }
        }
    }

    private fun showDatePickerDialog(calendar: Calendar?, viewId: Int) {
        viewModelScope.launch {
            _formEvent.emit(Event(FormEvent.DatePickerDialog(calendar, viewId)))
        }
    }
}