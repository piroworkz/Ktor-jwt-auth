package davidluna.com.app.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import davidluna.com.domain.JwtConfiguration
import io.ktor.http.auth.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val config: JwtConfiguration by inject()
    authentication {
        jwt(name = "auth-jwt") {
            realm = config.realm
            verifier(
                JWT.require(Algorithm.HMAC256(config.secret))
                    .withAudience(config.audience)
                    .withIssuer(config.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.audience)) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }

            challenge { _, realm ->
                val domain = config.domain
                val error = "Unauthorized"
                val description = "Access denied: invalid credentials"
                val authenticateHeader =
                    "Basic realm=\"$realm\", error=\"$error\", error_description=\"$description\", domain=\"$domain\""
                call.respond(UnauthorizedResponse(HttpAuthHeader.Single("WWW-Authenticate", authenticateHeader)))
            }
        }
    }
}


