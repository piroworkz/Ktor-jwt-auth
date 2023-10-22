package davidluna.com.app.plugins

import davidluna.com.app.framework.utils.buildFailResponse
import davidluna.com.domain.AppError.UnAuthorized
import davidluna.com.domain.Role
import davidluna.com.domain.User
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

class AuthorizationPlugin {

    operator fun invoke(): RouteScopedPlugin<Config> =
        createRouteScopedPlugin(
            name = this.javaClass.simpleName,
            createConfiguration = ::Config
        ) {
            pluginConfig.apply {
                on(AuthenticationChecked) { call ->
                    if (call.getRole() !in roles) {
                        call.respond(Unauthorized, buildFailResponse(UnAuthorized(401)))
                    }
                }
            }
        }

    private fun ApplicationCall.getRole(): Role =
        Role.valueOf(
            principal<JWTPrincipal>()
                ?.payload
                ?.getClaim(User::role.name)
                ?.asString() ?: error("No principal")
        )

    inner class Config {
        var roles: Set<Role> = emptySet()
    }
}
