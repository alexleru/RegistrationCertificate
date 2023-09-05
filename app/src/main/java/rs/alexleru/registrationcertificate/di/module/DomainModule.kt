package rs.alexleru.registrationcertificate.di.module

import dagger.Binds
import dagger.Module
import rs.alexleru.registrationcertificate.domain.usecase.AddDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.AddDocumentUseCaseImpl
import rs.alexleru.registrationcertificate.domain.usecase.DeleteDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.DeleteDocumentUseCaseImpl
import rs.alexleru.registrationcertificate.domain.usecase.GetDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.GetDocumentUseCaseImpl
import rs.alexleru.registrationcertificate.domain.usecase.GetListOfDocumentUseCase
import rs.alexleru.registrationcertificate.domain.usecase.GetListOfDocumentUseCaseImpl

@Module
interface DomainModule {

    @Binds
    fun bindsGetListOfDocumentUseCase(impl: GetListOfDocumentUseCaseImpl): GetListOfDocumentUseCase

    @Binds
    fun bindsGetDocumentUseCase(impl: GetDocumentUseCaseImpl): GetDocumentUseCase

    @Binds
    fun bindsAddDocumentUseCase(impl: AddDocumentUseCaseImpl): AddDocumentUseCase

    @Binds
    fun bindsDeleteDocumentUseCase(impl: DeleteDocumentUseCaseImpl): DeleteDocumentUseCase
}