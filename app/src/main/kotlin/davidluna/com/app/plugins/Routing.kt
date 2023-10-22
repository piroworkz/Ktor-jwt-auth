package davidluna.com.app.plugins

import davidluna.com.app.routes.adminRoutes
import davidluna.com.app.routes.authRouting
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        authRouting()
        adminRoutes()
    }
}