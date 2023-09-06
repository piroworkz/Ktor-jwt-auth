package davidluna.com.app.plugins

import davidluna.com.app.di.appDataModule
import davidluna.com.app.di.appModule
import davidluna.com.app.di.dataModule
import davidluna.com.app.di.useCasesModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.fileProperties
import org.koin.logger.slf4jLogger

fun configureKoinDi() {
    startKoin {
        slf4jLogger(Level.DEBUG)
        fileProperties("/koin.properties")
        modules(
            listOf(
                appModule,
                dataModule,
                useCasesModule,
                appDataModule
            )
        )
    }
}