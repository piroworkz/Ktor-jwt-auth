package davidluna.com.app.plugins

import davidluna.com.app.framework.utils.toRemote
import davidluna.com.app.routes.adminRoutes
import davidluna.com.app.routes.authRouting
import davidluna.com.data.buildSuccessResponse
import davidluna.com.domain.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    install(Resources)
    routing {
        get("/") {
            val response: Response<Index> = buildSuccessResponse(response = Index("Hello, World!", "Welcome to the Ktor API!"))
            call.respond(HttpStatusCode.OK, response.toRemote())
        }
        authRouting()
        adminRoutes()
    }
}

@Serializable
data class Index(
    val greeting: String,
    val message: String
)