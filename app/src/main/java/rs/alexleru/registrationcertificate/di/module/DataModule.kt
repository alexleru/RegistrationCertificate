package rs.alexleru.registrationcertificate.di.module

import dagger.Binds
import dagger.Module
import rs.alexleru.registrationcertificate.data.DocumentRepositoryImpl
import rs.alexleru.registrationcertificate.domain.DocumentRepository

@Module(includes = [DBModule::class])
interface DataModule {

    @Binds
    fun bindRepository(impl: DocumentRepositoryImpl): DocumentRepository
}