package rs.alexleru.registrationcertificate.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import rs.alexleru.registrationcertificate.di.key.ViewModelKey
import rs.alexleru.registrationcertificate.presentation.viewmodel.FormOfDocumentViewModel
import rs.alexleru.registrationcertificate.presentation.viewmodel.ListOfDocumentsViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(ListOfDocumentsViewModel::class)
    @Binds
    fun bindListOfDocumentsViewModel(impl: ListOfDocumentsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FormOfDocumentViewModel::class)
    @Binds
    fun bindFormOfDocumentViewModel(impl: FormOfDocumentViewModel): ViewModel
}