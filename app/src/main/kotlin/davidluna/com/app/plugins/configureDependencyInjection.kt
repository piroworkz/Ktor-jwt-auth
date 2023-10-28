package davidluna.com.app.plugins

import davidluna.com.app.di.AppDataModuleDI
import davidluna.com.app.di.AppModuleDI
import davidluna.com.app.di.DataModuleDI
import davidluna.com.app.di.Inject
import io.ktor.server.application.*

fun Application.configureDependencyInjection() {
    Inject.apply {
        appModule = AppModuleDI.build(this@configureDependencyInjection)
        appDataModule = AppDataModuleDI.build(appModule)
        dataModule = DataModuleDI.build(appDataModule)
    }
}