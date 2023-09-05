package rs.alexleru.registrationcertificate.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import rs.alexleru.registrationcertificate.di.key.AppScope
import rs.alexleru.registrationcertificate.di.module.DataModule
import rs.alexleru.registrationcertificate.di.module.DomainModule
import rs.alexleru.registrationcertificate.di.module.PresentationModule
import rs.alexleru.registrationcertificate.presentation.fragment.FormOfDocumentFragment
import rs.alexleru.registrationcertificate.presentation.fragment.ListOfDocumentsFragment

@AppScope
@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(listOfDocumentsFragment: ListOfDocumentsFragment)

    fun inject(formOfDocumentFragment: FormOfDocumentFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}