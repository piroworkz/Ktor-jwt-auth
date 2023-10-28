package davidluna.com.app

import davidluna.com.app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.jetty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    log.info("<----------------Starting application")
    configureDependencyInjection()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureContentNegotiation()
    configureRouting()
}

