package davidluna.com.app

import davidluna.com.app.di.appModule
import davidluna.com.app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.jetty.*
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.fileProperties
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    startKoin {
        slf4jLogger(Level.DEBUG)
        fileProperties("/koin.properties")
        modules(appModule)
    }
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureContentNegotiation()
    configureRouting()
}