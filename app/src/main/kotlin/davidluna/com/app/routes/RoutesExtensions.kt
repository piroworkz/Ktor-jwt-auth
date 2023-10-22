package davidluna.com.app.routes

import com.auth0.jwt.interfaces.Claim
import davidluna.com.domain.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.util.pipeline.*

fun PipelineContext<Unit, ApplicationCall>.getCredentials(): Triple<JWTPrincipal, Claim, Claim> {
    val principal: JWTPrincipal = call.principal<JWTPrincipal>() ?: error("No principal")
    val user = principal.payload.getClaim(User::username.name)
    val role = principal.payload.getClaim(User::role.name)
    return Triple(principal, user, role)
}