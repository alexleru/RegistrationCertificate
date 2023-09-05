package rs.alexleru.registrationcertificate

import android.app.Application
import rs.alexleru.registrationcertificate.di.component.DaggerAppComponent


class RegCertApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

}