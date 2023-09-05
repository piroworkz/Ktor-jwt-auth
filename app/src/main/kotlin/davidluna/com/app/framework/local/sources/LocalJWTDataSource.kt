package davidluna.com.app.framework.local.sources

import arrow.core.Either
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import davidluna.com.app.framework.utils.tryCatchSuspended
import davidluna.com.data.sources.JWTService
import davidluna.com.domain.AppError
import davidluna.com.domain.JWTClaim
import davidluna.com.domain.JwtConfiguration
import java.util.*

class LocalJWTDataSource(private val config: JwtConfiguration) : JWTService {
    override suspend fun generateToken(vararg claims: JWTClaim): Either<AppError, String> {
      return  tryCatchSuspended {
            val token = JWT.create()
                .withAudience(config.audience)
                .withIssuer(config.issuer)
                .withExpiresAt(Date(System.currentTimeMillis() + config.expiration))
            claims.forEach { claim ->
                token.withClaim(claim.name, claim.value)
            }

            token.sign(Algorithm.HMAC256(config.secret))
        }
    }
}