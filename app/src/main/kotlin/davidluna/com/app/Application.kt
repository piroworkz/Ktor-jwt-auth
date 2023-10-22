package davidluna.com.app

import davidluna.com.app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.jetty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureKoinDi()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureContentNegotiation()
    configureRouting()
}