package rs.alexleru.registrationcertificate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import rs.alexleru.registrationcertificate.domain.usecase.GetListOfDocumentUseCase
import rs.alexleru.registrationcertificate.presentation.state.ListState
import javax.inject.Inject

class ListOfDocumentsViewModel @Inject constructor(
    private val getListOfDocumentUseCase: GetListOfDocumentUseCase
) : ViewModel() {

    val listState: Flow<ListState> = getListOfDocumentUseCase.invoke()
        .map { ListState.Content(it) as ListState }
        .onStart { emit((ListState.Loading)) }
        .catch { emit(ListState.Error) }

}