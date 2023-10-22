package davidluna.com.app.routes

import davidluna.com.app.plugins.AuthorizationPlugin
import davidluna.com.domain.Role.ADMIN
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.adminRoutes() {
    authenticate("auth-jwt") {
        install(AuthorizationPlugin().invoke()) { roles = setOf(ADMIN) }

        get("/admin") {
            val (principal, user, role) = getCredentials()
            call.respond(
                HttpStatusCode.OK,
                "Only admins allowed here! Welcome $user, your assigned role is: $role Token expires: ${principal.payload.expiresAt}"
            )
        }
    }
}