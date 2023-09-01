package davidluna.com.app.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import davidluna.com.app.framework.model.RemoteResponse
import davidluna.com.app.framework.model.RemoteStatusCode
import davidluna.com.domain.JwtConfiguration
import io.ktor.http.*
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
                call.respond(HttpStatusCode.Unauthorized, buildFailAuthResponse())
            }
        }
        basic("auth-basic") {
//            config basic auth
        }
    }
}


private fun buildFailAuthResponse(): RemoteResponse<RemoteStatusCode> {
    return RemoteResponse(
        code = RemoteStatusCode(
            HttpStatusCode.Unauthorized.value,
            HttpStatusCode.Unauthorized.description
        ),
        message = "Access denied: invalid credentials",
        token = "",
        body = RemoteStatusCode(
            HttpStatusCode.Unauthorized.value,
            HttpStatusCode.Unauthorized.description
        )
    )
}
