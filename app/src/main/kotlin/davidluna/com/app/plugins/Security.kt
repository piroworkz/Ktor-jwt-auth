package davidluna.com.app.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import davidluna.com.app.model.SerializedResponse
import davidluna.com.app.model.SerializedStatusCode
import davidluna.com.domain.JwtConfiguration
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
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
            challenge { _, _ ->
                call.respond(Unauthorized, buildFailAuthResponse())
            }
        }
    }
}


private fun buildFailAuthResponse(): SerializedResponse<SerializedStatusCode> {
    return SerializedResponse(
        code = SerializedStatusCode(
            Unauthorized.value,
            Unauthorized.description
        ),
        message = "Access denied: invalid credentials",
        token = "",
        body = SerializedStatusCode(
            Unauthorized.value,
            Unauthorized.description
        )
    )
}
