package rs.alexleru.registrationcertificate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rs.alexleru.registrationcertificate.domain.usecase.AddDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.GetDocumentUseCase
import rs.alexleru.registrationcertificate.presentation.state.DocumentState
import javax.inject.Inject

class FormOfDocumentViewModel
@Inject constructor(
    private val getDocumentUseCase: GetDocumentUseCase,
    private val addDocumentUseCase: AddDocumentUseCase,
//    deleteDocumentUseCase: DeleteDocumentUseCase
) : ViewModel() {


    private val _documentState = MutableStateFlow<DocumentState>(DocumentState.Loading)
    val documentState = _documentState.asStateFlow()

    fun getDocument(documentId: Long) {
        viewModelScope.launch {
            val item = getDocumentUseCase.invoke(documentId)
            _documentState.value = DocumentState.Content(item)
        }
    }


}