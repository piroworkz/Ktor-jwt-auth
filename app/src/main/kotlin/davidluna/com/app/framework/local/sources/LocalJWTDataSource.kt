package davidluna.com.app.framework.local.sources

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import davidluna.com.domain.JWTClaim
import davidluna.com.domain.JwtConfiguration
import davidluna.com.data.sources.JWTService
import java.util.*

class LocalJWTDataSource(private val config: JwtConfiguration) : JWTService {
    override fun generateToken(vararg claims: JWTClaim): String {
        val token = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiration))
        claims.forEach { claim ->
            token.withClaim(claim.name, claim.value)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }

}