package rs.alexleru.registrationcertificate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rs.alexleru.registrationcertificate.di.key.AppScope
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class DocumentViewModelProvider @Inject constructor(
    private val mapProvider: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mapProvider[modelClass]?.get() as T
    }
}