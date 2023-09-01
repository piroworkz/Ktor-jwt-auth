package davidluna.com.app

import davidluna.com.app.di.appModule
import davidluna.com.app.plugins.*
import io.ktor.server.application.*
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.fileProperties
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    startKoin {
        slf4jLogger(Level.DEBUG)
        fileProperties("/koin.properties")
        modules(appModule)
    }
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureContentNegotiation()
    configureRouting()
}

